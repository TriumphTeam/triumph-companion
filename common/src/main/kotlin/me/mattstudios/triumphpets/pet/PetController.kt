package me.mattstudios.triumphpets.pet

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author Matt
 */
interface PetController {

    /**
     * Checks if the entity is a pet or not
     */
    fun isPet(entity: Entity): Boolean

    fun isPetComponent(entity: Entity): Boolean

    /**
     * Spawns a new pet on the location
     */
    fun spawnPet(location: Location, player: Player)

    /**
     * Removes all the pets from the world
     */
    fun removeAll()
}