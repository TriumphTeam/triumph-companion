package me.mattstudios.triumphpets.pet

import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author Matt
 */
interface Pet {

    fun getMemory(): PetMemory

    fun getInventory(): PetInventory

    fun getOwner(): Player

    fun getEntity(): Entity

}