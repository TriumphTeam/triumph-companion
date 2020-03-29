package me.mattstudios.triumphpets.pet.v1_15

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.pet.components.PetNameEntity
import me.mattstudios.triumphpets.pet.v1_15.components.NameEntity
import net.minecraft.server.v1_15_R1.World
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity
import org.bukkit.entity.Entity

/**
 * @author Matt
 */
class CrateEntityController(private val plugin: MattPlugin) : CrateController {

    private val holograms = mutableListOf<Entity>()

    /**
     * Spawns the crate entities
     */
    override fun spawnCrateEntities(location: Location, lines: List<String>) {
        val world = (location.world as CraftWorld).handle
        val lineLocation = location.clone().add(.5, .5, .5)

        // Spawns each line in their according location, reversed
        for (line in lines.asReversed()) {
            spawnEntity(lineLocation, line, world)
            lineLocation.add(0.0, .25, 0.0)
        }
    }

    /**
     * Checks if the entity is a crate entity
     */
    override fun isCrateEntity(entity: Entity): Boolean {
        return (entity as CraftEntity).handle is PetNameEntity
    }

    /**
     * Respawns the entity if it dies
     */
    override fun respawnEntity(entity: Entity) {
        holograms.remove(entity)

        val world = (entity.location.world as CraftWorld).handle
        spawnEntity(entity.location, entity.customName ?: "", world)
    }

    /**
     * Removes all the holograms
     */
    override fun remove() {
        holograms.forEach { it.remove() }
        holograms.clear()
    }

    /**
     * Spawns the hologram entity
     */
    private fun spawnEntity(location: Location, line: String, world: World) {
        val hologram = NameEntity(plugin, line, "pet-crate", world)
        hologram.setLocation(location.x, location.y, location.z, 0f, 0f)

        world.addEntity(hologram)

        holograms.add(hologram.bukkitEntity)
    }
}