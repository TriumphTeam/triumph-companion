package me.mattstudios.triumphpets.crate

import org.bukkit.entity.Entity

/**
 * @author Matt
 */
interface CrateController {

    /**
     * Spawns a name entity in the location
     */
    fun spawnCrateEntities(crate: Crate, lines: List<String>)

    /**
     * Checks if the entity is a crate entity
     */
    fun isCrateEntity(entity: Entity): Boolean

    /**
     * Respawns the entities when they die
     */
    fun respawnEntity(entity: Entity)

    /**
     * Removes the holograms
     */
    fun remove(crate: Crate)

    fun hide(crate: Crate)

    fun show(crate: Crate)
}