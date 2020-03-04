package me.mattstudios.triumphpets.data.database

import me.mattstudios.triumphpets.data.PetData

/**
 * @author Matt
 */
interface Database {

    fun insertPet(petData: PetData)

}