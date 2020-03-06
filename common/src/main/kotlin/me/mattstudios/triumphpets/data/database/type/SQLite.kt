package me.mattstudios.triumphpets.data.database.type

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.Task.async
import me.mattstudios.mattcore.utils.Task.asyncTimer
import me.mattstudios.mattcore.utils.Task.later
import me.mattstudios.mattcore.utils.Task.laterAsync
import me.mattstudios.mattcore.utils.Task.timer
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.Queries
import me.mattstudios.triumphpets.data.database.Queries.SQLITE_CREATE_PETS
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.manager.DataManager
import me.mattstudios.triumphpets.pet.components.PetExperience
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.Bukkit
import org.sqlite.SQLiteDataSource
import java.io.File
import java.io.IOException
import java.sql.Connection
import java.sql.SQLException
import java.util.UUID
import kotlin.concurrent.timerTask


/**
 * @author Matt
 */
class SQLite(private val plugin: MattPlugin, private val dataManager: DataManager) : Database {

    private lateinit var dataSource: SQLiteDataSource

    /**
     * Initializes everything and caches it
     */
    init {
        createDB()
        createTables()
        cacheData()
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
            //connection.prepareStatement(SQLITE_CREATE_PET_INVENTORY).execute()
        } catch (e: SQLException) {
            plugin.locale.sendMessage(Message.STARTUP_CREATE_TABLES_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null) {
                try {
                    connection.close()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Caches all the data
     */
    private fun cacheData() {

        cachePets()

    }

    /**
     * Caches the pet's data
     */
    private fun cachePets() {
        var connection: Connection? = null

        try {
            connection = dataSource.connection
            val resultSet = connection.createStatement().executeQuery(Queries.SQLITE_SELECT_PETS)

            while (resultSet.next()) {
                val uuid = UUID.fromString(resultSet.getString("uuid"))
                val ownerUuid = UUID.fromString(resultSet.getString("owner_uuid"))
                val petType = PetType.valueOf(resultSet.getString("type"))
                val name = resultSet.getString("name")
                val experience = PetExperience(resultSet.getInt("experience"))

                dataManager.loadPet(PetData(uuid, ownerUuid, petType, name, experience))
            }

            resultSet.close()
        } catch (e: SQLException) {
            plugin.locale.sendMessage(Message.STARTUP_CACHE_PETS_ERROR)
            e.printStackTrace()
        } finally {
            if (connection != null) {
                try {
                    connection.close()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Inserts the pet in the database
     */
    override fun insertPet(petData: PetData) {
        async {
            var connection: Connection? = null

            try {
                connection = dataSource.connection
                val statement = connection.prepareStatement(Queries.SQLITE_INSERT_PET)
                statement.setString(1, petData.uuid.toString())
                statement.setString(2, petData.owner.uniqueId.toString())
                statement.setString(3, petData.type.toString())
                statement.setString(4, petData.name)
                statement.setInt(5, petData.experience.xp)

                statement.executeUpdate()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                if (connection != null) {
                    try {
                        connection.close()
                    } catch (e: SQLException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}