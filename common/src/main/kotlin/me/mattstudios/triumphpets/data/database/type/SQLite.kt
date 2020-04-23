package me.mattstudios.triumphpets.data.database.type

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.info
import me.mattstudios.mattcore.utils.Task.async
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.componetents.CrateEffect
import me.mattstudios.triumphpets.crate.componetents.CrateEgg
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
import me.mattstudios.triumphpets.data.database.util.DatabaseUtils.tryRun
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.managers.CrateManager
import me.mattstudios.triumphpets.managers.DataManager
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
import java.util.UUID


/**
 * @author Matt
 */
class SQLite(private val plugin: MattPlugin) : Database {

    private val locale = plugin.locale
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
                if (dbFile.createNewFile()) locale.sendMessage(Message.STARTUP_CREATE_DATABASE_SUCCESS)
                else locale.sendMessage(Message.STARTUP_CREATE_DATABASE_ERROR)
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
        dataSource.connection.tryRun(locale.getMessage(Message.STARTUP_CREATE_TABLES_ERROR)) { connection ->
            connection.prepareStatement(SQLITE_CREATE_PETS).execute()
            connection.prepareStatement(SQLITE_CREATE_PLAYERS).execute()
            connection.prepareStatement(SQLITE_CREATE_CRATES).execute()
        }
    }

    /**
     * Caches the player's data
     */
    override fun cachePlayers(dataManager: DataManager) {
        dataSource.connection.tryRun(locale.getMessage(Message.STARTUP_CACHE_PETS_ERROR)) { connection ->
            connection.createStatement().executeQuery(SQLITE_SELECT_PLAYERS).use { resultSet ->
                while (resultSet.next()) {
                    val uuid = UUID.fromString(resultSet.getString("uuid"))
                    val activePet = resultSet.getString("active_pet")
                    val activePetUUID: UUID? = if (activePet != "null") UUID.fromString(activePet) else null

                    val petPlayer = PetPlayer(uuid, activePetUUID)
                    cachePets(petPlayer, dataManager.petConfig)
                    dataManager.loadPlayer(petPlayer)
                }
            }
        }
    }

    /**
     * Caches the player's data
     */
    // TODO CHANGES
    override fun cacheCrates(crateManager: CrateManager) {
        dataSource.connection.tryRun(locale.getMessage(Message.STARTUP_CACHE_PETS_ERROR)) { connection ->
            connection.createStatement().executeQuery(SQLITE_SELECT_CRATES).use { resultSet ->
                while (resultSet.next()) {
                    val uuid = UUID.fromString(resultSet.getString("uuid"))
                    val location = stringToBlockLocation(resultSet.getString("location"))
                    val crateEgg = CrateEgg.valueOf(resultSet.getString("crate_egg"))
                    val crateEffect = CrateEffect.valueOf(resultSet.getString("crate_effect"))

                    // Errors if any of those are null
                    if (location == null) {
                        info("Error loading this crate")
                        continue
                    }

                    val crate = Crate(uuid, location, crateEgg, crateEffect, null)
                    crateManager.loadCrate(crate)
                }
            }
        }
    }

    /**
     * Caches the pet's data
     */
    private fun cachePets(petPlayer: PetPlayer, petConfig: PetConfig) {
        dataSource.connection.tryRun(locale.getMessage(Message.STARTUP_CACHE_PETS_ERROR)) { connection ->
            val statement = connection.prepareStatement(SQLITE_SELECT_PETS)
            statement.setString(1, petPlayer.player.uniqueId.toString())

            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    val uuid = UUID.fromString(resultSet.getString("uuid"))
                    val petType = PetType.valueOf(resultSet.getString("type"))
                    val name = resultSet.getString("name")
                    val petExperience = PetExperience(resultSet.getInt("experience"))

                    val petMemory = PetMemory(plugin, petConfig, FilterType.BLACK_LIST, petExperience)

                    petPlayer.addPet(PetData(plugin, uuid, petType, name, petMemory, petPlayer.player))
                }
            }
        }
    }

    /**
     * Inserts the player in the database
     */
    override fun insertPlayer(petPlayer: PetPlayer) {
        async {
            // TODO error message
            dataSource.connection.tryRun { connection ->
                val statement = connection.prepareStatement(SQLITE_INSERT_PLAYER)
                statement.setString(1, petPlayer.player.uniqueId.toString())
                statement.setString(2, petPlayer.activePetUUID.toString())

                statement.executeUpdate()
            }
        }
    }

    /**
     * Inserts the player in the database
     */
    override fun insertCrate(crate: Crate): Boolean {
        var inserted = false

        // TODO error message
        dataSource.connection.tryRun("&aFuck me") { connection ->
            val statement = connection.prepareStatement(SQLITE_INSERT_CRATE)
            statement.setString(1, crate.uuid.toString())
            statement.setString(2, blockLocationToString(crate.location))
            statement.setString(3, crate.crateEgg.name)
            statement.setString(4, crate.crateEffect.name)

            statement.executeUpdate()
            inserted = true
        }

        return inserted
    }

    /**
     * Inserts the pet in the database
     */
    override fun insertPet(petPlayer: PetPlayer, petData: PetData) {
        async {
            dataSource.connection.tryRun { connection ->
                val statement = connection.prepareStatement(SQLITE_INSERT_PET)
                statement.setString(1, petData.uuid.toString())
                statement.setString(2, petPlayer.player.uniqueId.toString())
                statement.setString(3, petData.type.toString())
                statement.setString(4, petData.name)
                statement.setInt(5, petData.petMemory.petExperience.xp)

                statement.executeUpdate()
            }
        }
    }

    /**
     * Inserts the pet in the database
     */
    override fun removeCrate(crate: Crate) {
        async {
            dataSource.connection.tryRun { connection ->
                val statement = connection.prepareStatement(SQLITE_REMOVE_CRATE)
                statement.setString(1, crate.uuid.toString())
                statement.executeUpdate()
            }
        }
    }

}