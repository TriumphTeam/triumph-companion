package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.type.SQLite

/**
 * @author Matt
 */
class DataManager(private val plugin: MattPlugin, private val dbType: DBType) {

    private lateinit var database: Database
    private val petsData = mutableSetOf<PetData>()

    init {
        when (dbType) {
            DBType.SQLITE -> database = SQLite(plugin, this)
        }
    }

    /**
     * Loads the data from the database to the set
     */
    fun loadPet(petData: PetData) {
        petsData.add(petData)
    }

    /**
     * Adds the new pet to the set and to the database
     */
    fun addPet(petData: PetData) {
        database.insertPet(petData)
        petsData.add(petData)
    }

}