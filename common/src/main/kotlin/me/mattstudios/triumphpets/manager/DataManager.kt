package me.mattstudios.triumphpets.manager

import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.pet.PetPlayer
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class DataManager(private val database: Database, val petConfig: PetConfig) {

    private val petPlayers = mutableSetOf<PetPlayer>()

    /**
     * Loads the data from the database to the set
     */
    fun loadPlayer(player: PetPlayer) {
        petPlayers.add(player)
    }

    /**
     * Adds the player to the database and the list
     */
    fun addPlayer(petPlayer: PetPlayer) {
        database.insertPlayer(petPlayer)
        petPlayers.add(petPlayer)
    }

    /**
     * Gets the pet player
     */
    fun getPetPlayer(player: Player): PetPlayer? {
        return petPlayers.find { it.isPetPlayer(player) }
    }

    /**
     * Adds the new pet to the set and to the database
     */
    fun addPet(petPlayer: PetPlayer, petData: PetData) {
        database.insertPet(petPlayer, petData)
        petPlayer.addPet(petData)
    }

}