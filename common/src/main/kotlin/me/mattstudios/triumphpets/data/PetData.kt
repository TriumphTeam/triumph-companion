package me.mattstudios.triumphpets.data

import me.mattstudios.triumphpets.pet.components.PetExperience
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.Bukkit
import java.util.UUID

/**
 * @author Matt
 */
class PetData(
        val uuid: UUID, ownerUuid: UUID, val type: PetType,
        var name: String, var experience: PetExperience) {

    var owner = Bukkit.getOfflinePlayer(ownerUuid)
    var spawned = false

    constructor(uuid: UUID, ownerUuid: UUID, petType: PetType, name: String) :
            this(uuid, ownerUuid, petType, name, PetExperience(0))

}