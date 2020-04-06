package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.type.SQLite
import me.mattstudios.triumphpets.pet.PetController


/**
 * @author Matt
 */
class PetManager(private val plugin: TriumphPets) {

    // The controller for the pet entity
    lateinit var petController: PetController
    // The crate entity controller
    lateinit var crateController: CrateController

    val database: Database

    val crateManager: CrateManager
    val dataManager: DataManager


    init {
        when (getServerVersion()) {
            "v1_15_R1" -> {
                petController = me.mattstudios.triumphpets.pet.v1_15.EntityController(plugin)
                crateController = me.mattstudios.triumphpets.pet.v1_15.CrateEntityController(plugin)
            }

            else -> println("shit boy")
        }

        // Removes entities left behind in case the server crashes
        petController.removeCrash()

        // Initializes the database TODO turn it into a when
        database = SQLite(plugin)

        // Initializes the data and crate managers
        dataManager = DataManager(database, plugin.petConfig)
        crateManager = CrateManager(crateController, database)

        // Caches all the data
        database.cachePlayers(dataManager)
        database.cacheCrates(crateManager)

        // Checks if there was a mistake and crate block is missing
        crateManager.checkStartupMissing()
    }

    /**
     * Runs on server disable to remove entities from the world
     */
    fun disable() {
        petController.removeAll()
        crateManager.removeAll()
    }

}