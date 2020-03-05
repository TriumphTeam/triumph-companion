package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.pet.PetController


/**
 * @author Matt
 */
class PetManager(private val plugin: TriumphPets) {

    lateinit var petController: PetController
    val dataManager = DataManager(plugin, DBType.SQLITE)

    init {
        when (getServerVersion()) {
            "v1_15_R1" -> petController = me.mattstudios.triumphpets.pet.v1_15.EntityController(plugin, plugin.petConfig)
            else -> println("shit boy")
        }

        petController.removeCrash()
    }

}