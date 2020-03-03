package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.SQLite

/**
 * @author Matt
 */
class DatabaseManager(private val plugin: MattPlugin, private val dbType: DBType) {

    private lateinit var database: Database

    init {
        when (dbType) {
            DBType.SQLITE -> database = SQLite(plugin)
        }
    }


}