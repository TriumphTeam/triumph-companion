package me.mattstudios.triumphpets.crate

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * @author Matt
 */
interface CrateController {

    /**
     * Spawns a name entity in the location
     */
    fun spawnCrateEntities(location: Location, lines: List<String>)

    /**
     * Checks if the entity is a crate entity
     */
    fun isCrateEntity(entity: Entity): Boolean

    /**
     * Respawns the entities when they die
     */
    fun respawnEntities()
}