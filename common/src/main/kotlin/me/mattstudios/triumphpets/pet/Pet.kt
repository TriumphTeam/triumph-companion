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
    fun getOwner(): Player

    /**
     * Gets the pet bukkit entity
     */
    fun getEntity(): Entity

}