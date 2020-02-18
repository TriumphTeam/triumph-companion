package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.pet.PetController


/**
 * @author Matt
 */
class PetManager(private val plugin: TriumphPets) {

    lateinit var petController: PetController

    init {
        when (getServerVersion()) {
            "v1_15_R1" -> petController = me.mattstudios.triumphpets.pet.v1_15.EntityController(plugin)
            else -> println("shit boy")
        }

        petController.removeCrash()
    }

}