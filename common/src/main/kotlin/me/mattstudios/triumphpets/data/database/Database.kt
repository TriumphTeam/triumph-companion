package me.mattstudios.triumphpets.data.database

import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.managers.CrateManager
import me.mattstudios.triumphpets.managers.DataManager
import me.mattstudios.triumphpets.pet.PetPlayer

/**
 * @author Matt
 */
interface Database {

    fun cachePlayers(dataManager: DataManager)

    fun cacheCrates(crateManager: CrateManager)

    fun insertPlayer(petPlayer: PetPlayer)

    fun insertPet(petPlayer: PetPlayer, petData: PetData)

    fun insertCrate(crate: Crate): Boolean

    fun editCrate(crate: Crate)

    fun removeCrate(crate: Crate)
}