package me.mattstudios.triumphpets.data.database.type

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.info
import me.mattstudios.mattcore.utils.Task.async
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_CREATE_CRATES
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_CREATE_PETS
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_CREATE_PLAYERS
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_INSERT_CRATE
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_INSERT_PET
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_INSERT_PLAYER
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_REMOVE_CRATE
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_SELECT_CRATES
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_SELECT_PETS
import me.mattstudios.triumphpets.data.database.queries.SQLiteQueries.SQLITE_SELECT_PLAYERS
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.manager.CrateManager
import me.mattstudios.triumphpets.manager.DataManager
import me.mattstudios.triumphpets.pet.PetPlayer
import me.mattstudios.triumphpets.pet.components.FilterType
import me.mattstudios.triumphpets.pet.components.PetExperience
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet.utils.PetType
import me.mattstudios.triumphpets.util.Utils.blockLocationToString
import me.mattstudios.triumphpets.util.Utils.stringToBlockLocation
import org.sqlite.SQLiteDataSource
import java.io.File
import java.io.IOException
import java.sql.Connection
import java.sql.SQLException
import java.util.UUID


/**
 * @author Matt
 */
class SQLite(private val plugin: MattPlugin) : Database {

    private lateinit var dataSource: SQLiteDataSource

    /**
     * Initializes everything and caches it
     */
    init {
        createDB()
        createTables()
    }

    /**
     * Connects to the database
     */
    private fun connect() {
        dataSource = SQLiteDataSource()
        dataSource.url = "jdbc:sqlite:" + plugin.dataFolder.toString() + "/pets.db"
    }

    /**
     * Creates the database if not exist
     */
    private fun createDB() {
        try {
            val dbFile = File(plugin.dataFolder, "pets.db")

            if (!dbFile.exists()) {
                if (dbFile.createNewFile()) plugin.locale.sendMessage(Message.STARTUP_CREATE_DATABASE_SUCCESS)
                else plugin.locale.sendMessage(Message.STARTUP_CREATE_DATABASE_ERROR)
            }

            connect()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Creates all the default tables
     */
    private fun createTables() {
        var connection: Connection? = null
        try {
            connection = dataSource.connection

            connection.prepareStatement(SQLITE_CREATE_PETS).execute()
            connection.prepareStatement(SQLITE_CREATE_PLAYERS).execute()
            connection.prepareStatement(SQLITE_CREATE_CRATES).execute()
        } catch (e: SQLException) {
            plugin.locale.sendMessage(Message.STARTUP_CREATE_TABLES_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    /**
     * Caches the player's data
     */
    override fun cachePlayers(dataManager: DataManager) {
        var connection: Connection? = null

        try {
            connection = dataSource.connection
            val resultSet = connection.createStatement().executeQuery(SQLITE_SELECT_PLAYERS)

            while (resultSet.next()) {
                val uuid = UUID.fromString(resultSet.getString("uuid"))
                val activePet = resultSet.getString("active_pet")
                val activePetUUID: UUID? = if (activePet != "null") UUID.fromString(activePet) else null

                val petPlayer = PetPlayer(uuid, activePetUUID)
                cachePets(petPlayer, dataManager.petConfig)
                dataManager.loadPlayer(petPlayer)
            }

            resultSet.close()
        } catch (e: SQLException) {
            // TODO change this for player
            plugin.locale.sendMessage(Message.STARTUP_CACHE_PETS_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    /**
     * Caches the player's data
     */
    override fun cacheCrates(crateManager: CrateManager) {
        var connection: Connection? = null

        try {
            connection = dataSource.connection
            val resultSet = connection.createStatement().executeQuery(SQLITE_SELECT_CRATES)

            while (resultSet.next()) {
                val uuid = UUID.fromString(resultSet.getString("uuid"))
                val location = stringToBlockLocation(resultSet.getString("location"))

                if (location == null) {
                    info("Error loading this crate")
                    continue
                }

                val crate = Crate(uuid, location)
                crateManager.loadCrate(crate)
            }

            resultSet.close()
        } catch (e: SQLException) {
            // TODO change this for player
            plugin.locale.sendMessage(Message.STARTUP_CACHE_PETS_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    /**
     * Caches the pet's data
     */
    private fun cachePets(petPlayer: PetPlayer, petConfig: PetConfig) {
        var connection: Connection? = null

        try {
            connection = dataSource.connection
            val statement = connection.prepareStatement(SQLITE_SELECT_PETS)
            statement.setString(1, petPlayer.player.uniqueId.toString())

            val resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val uuid = UUID.fromString(resultSet.getString("uuid"))
                val petType = PetType.valueOf(resultSet.getString("type"))
                val name = resultSet.getString("name")
                val petExperience = PetExperience(resultSet.getInt("experience"))

                val petMemory = PetMemory(plugin, petConfig, FilterType.BLACK_LIST, petExperience)

                petPlayer.addPet(PetData(plugin, uuid, petType, name, petMemory, petPlayer.player))
            }

            resultSet.close()
        } catch (e: SQLException) {
            plugin.locale.sendMessage(Message.STARTUP_CACHE_PETS_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    /**
     * Inserts the player in the database
     */
    override fun insertPlayer(petPlayer: PetPlayer) {
        async {
            var connection: Connection? = null

            try {
                connection = dataSource.connection
                val statement = connection.prepareStatement(SQLITE_INSERT_PLAYER)
                statement.setString(1, petPlayer.player.uniqueId.toString())
                statement.setString(2, petPlayer.activePetUUID.toString())

                statement.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                if (connection != null && !connection.isClosed) {
                    connection.close()
                }
            }
        }
    }

    /**
     * Inserts the player in the database
     */
    override fun insertCrate(crate: Crate) {
        async {
            var connection: Connection? = null

            try {
                connection = dataSource.connection
                val statement = connection.prepareStatement(SQLITE_INSERT_CRATE)
                statement.setString(1, crate.uuid.toString())
                statement.setString(2, blockLocationToString(crate.location))

                statement.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                if (connection != null && !connection.isClosed) {
                    connection.close()
                }
            }
        }
    }

    /**
     * Inserts the pet in the database
     */
    override fun insertPet(petPlayer: PetPlayer, petData: PetData) {
        async {
            var connection: Connection? = null

            try {
                connection = dataSource.connection
                val statement = connection.prepareStatement(SQLITE_INSERT_PET)
                statement.setString(1, petData.uuid.toString())
                statement.setString(2, petPlayer.player.uniqueId.toString())
                statement.setString(3, petData.type.toString())
                statement.setString(4, petData.name)
                statement.setInt(5, petData.petMemory.petExperience.xp)

                statement.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                if (connection != null && !connection.isClosed) {
                    connection.close()
                }
            }
        }
    }

    /**
     * Inserts the pet in the database
     */
    override fun removeCrate(crate: Crate) {
        async {
            var connection: Connection? = null

            try {
                connection = dataSource.connection
                val statement = connection.prepareStatement(SQLITE_REMOVE_CRATE)
                statement.setString(1, crate.uuid.toString())
                statement.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                if (connection != null && !connection.isClosed) {
                    connection.close()
                }
            }
        }
    }

}