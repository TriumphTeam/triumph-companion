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
    lateinit var database: Database

    lateinit var crateManager: CrateManager
    lateinit var dataManager: DataManager


    init {
        when (getServerVersion()) {
            "v1_15_R1" -> {
                petController = me.mattstudios.triumphpets.pet.v1_15.EntityController(plugin)
                crateController = me.mattstudios.triumphpets.pet.v1_15.CrateEntityController(plugin)
            }

            else -> println("shit boy")
        }

        database = SQLite(plugin)

        dataManager = DataManager(database, plugin.petConfig)
        crateManager = CrateManager(crateController, database)

        database.cachePlayers(dataManager)
        database.cacheCrates(crateManager)


        petController.removeCrash()
    }

}