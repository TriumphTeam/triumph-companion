package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.pet.PetController


/**
 * @author Matt
 */
class PetManager(private val plugin: TriumphPets) {

    // The controller for the pet entity
    lateinit var petController: PetController
    lateinit var crateController: CrateController
    lateinit var crate: Crate
    val dataManager = DataManager(plugin, DBType.SQLITE, plugin.petConfig)


    init {
        when (getServerVersion()) {
            "v1_15_R1" -> {
                petController = me.mattstudios.triumphpets.pet.v1_15.EntityController(plugin)
                crateController = me.mattstudios.triumphpets.pet.v1_15.CrateEntityController(plugin)
            }

            else -> println("shit boy")
        }

        crate = Crate(crateController)

        petController.removeCrash()
    }

}