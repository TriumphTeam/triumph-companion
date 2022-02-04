package me.mattstudios.triumphpets.pet

import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author Matt
 */
interface Pet {

    /**
     * Gets the pet's name
     */
    val petName: String

    /**
     * Gets the pet's level
     */
    val level: Short

    /**
     * Gets the pet memory
     */
    val petMemory: PetMemory

    /**
     * Gets the pet inventory
     */
    val petInventory: PetInventory

    /**
     * Gets the pet owner
     */
    val petOwner: Player

    /**
     * Checks if the specific player is owner
     */
    fun isOwner(player: Player): Boolean

    /**
     * Gets the pet bukkit entity
     */
    val entity: Entity

    /*fun getXp(): Int

    fun addXp(xp: Int)*/

    /**
     * Removes this entity and the Name entity too
     */
    fun remove()
}