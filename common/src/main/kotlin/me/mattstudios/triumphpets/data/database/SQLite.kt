package me.mattstudios.triumphpets.data.database

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.info
import me.mattstudios.triumphpets.data.database.Queries.SQLITE_CREATE_PETS
import me.mattstudios.triumphpets.data.database.Queries.SQLITE_CREATE_PET_INVENTORY
import org.sqlite.SQLiteDataSource
import java.io.File
import java.io.IOException
import java.sql.Connection
import java.sql.SQLException

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
                if (!dbFile.createNewFile()) info("&cCouldn't create database file.")
                else info("&aDatabase created successfully!")
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
            connection.prepareStatement(SQLITE_CREATE_PET_INVENTORY).execute()
        } catch (e: SQLException) {
            info("&cAn error occurred creating database tables!")
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

        /*cacheTown()
        cacheTownPlayer()
        cacheClaims()*/

    }

}