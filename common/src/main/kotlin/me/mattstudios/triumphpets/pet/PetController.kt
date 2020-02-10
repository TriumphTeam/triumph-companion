package me.mattstudios.triumphpets.pet

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author Matt
 */
interface PetController {

    fun isPet(entity: Entity): Boolean

    fun spawnPet(location: Location, player: Player)

    fun removeAll()
}