package me.mattstudios.triumphpets.data

import me.mattstudios.triumphpets.pet.components.PetExperience
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.Bukkit
import java.util.UUID

/**
 * @author Matt
 */
class PetData(
        val uuid: UUID, val type: PetType,
        var name: String, var experience: PetExperience) {

    constructor(uuid: UUID, petType: PetType, name: String) :
            this(uuid, petType, name, PetExperience(0))

}