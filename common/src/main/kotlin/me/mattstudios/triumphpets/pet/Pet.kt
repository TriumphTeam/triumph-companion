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
    fun getName(): String

    /**
     * Gets the pet's level
     */
    fun getLevel(): Short

    /**
     * Gets the pet memory
     */
    fun getMemory(): PetMemory

    /**
     * Gets the pet inventory
     */
    fun getInventory(): PetInventory

    /**
     * Gets the pet owner
     */
    fun getPetOwner(): Player

    /**
     * Checks if the specific player is owner
     */
    fun isOwner(player: Player): Boolean

    /**
     * Gets the pet bukkit entity
     */
    fun getEntity(): Entity

    /*fun getXp(): Int

    fun addXp(xp: Int)*/

    /**
     * Removes this entity and the Name entity too
     */
    fun remove()
}