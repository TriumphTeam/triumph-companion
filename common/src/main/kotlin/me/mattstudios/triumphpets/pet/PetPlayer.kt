package me.mattstudios.triumphpets.pet

import me.mattstudios.triumphpets.data.PetData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
class PetPlayer(uuid: UUID, var activePetUUID: UUID?) {

    val pets = mutableSetOf<PetData>()
    val player = Bukkit.getOfflinePlayer(uuid)

    constructor(uuid: UUID) : this(uuid, null)

    fun addPet(petData: PetData) {
        pets.add(petData)
    }

    fun isPetPlayer(player: Player): Boolean {
        return this.player.uniqueId == player.uniqueId
    }
}