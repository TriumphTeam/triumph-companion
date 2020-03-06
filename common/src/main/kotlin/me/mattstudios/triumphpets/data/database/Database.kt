package me.mattstudios.triumphpets.data.database

import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.pet.PetPlayer

/**
 * @author Matt
 */
interface Database {

    fun insertPlayer(petPlayer: PetPlayer)
    fun insertPet(petData: PetData)

}