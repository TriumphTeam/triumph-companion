package me.mattstudios.triumphpets.pet

import me.mattstudios.triumphpets.data.PetData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
class PetPlayer(uuid: UUID, var activePetUUID: UUID?) {

    val pets = mutableListOf<PetData>()
    val player = Bukkit.getOfflinePlayer(uuid)

    constructor(uuid: UUID) : this(uuid, null)

    /**
     * Adds the pet to the player
     */
    fun addPet(petData: PetData) {
        pets.add(petData)
    }

    /**
     * Checks if the player is a pet player
     */
    fun isPetPlayer(player: Player): Boolean {
        return this.player.uniqueId == player.uniqueId
    }

    /**
     * Gets the active pet
     */
    fun getActivePet(): PetData? {
        return pets.find { activePetUUID == it.uuid }
    }

    /**
     * Checks if it's the active pet
     */
    fun isActivePet(petData: PetData): Boolean {
        return activePetUUID == petData.uuid
    }
}