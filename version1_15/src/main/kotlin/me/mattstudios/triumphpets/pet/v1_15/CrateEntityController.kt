package me.mattstudios.triumphpets.pet.v1_15

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.crate.Crate
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

    private val holograms = mutableMapOf<Crate, MutableList<Entity>>()

    /**
     * Spawns the crate entities
     */
    override fun spawnCrateEntities(crate: Crate, lines: List<String>) {
        val world = (crate.location.world as CraftWorld).handle
        val lineLocation = crate.location.clone().add(.5, .5, .5)

        // Spawns each line in their according location, reversed
        for (line in lines.asReversed()) {
            spawnEntity(crate, lineLocation, line, world)
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
        for ((crate, list) in holograms) {
            if (!list.remove(entity)) continue

            val world = (entity.location.world as CraftWorld).handle
            spawnEntity(crate, entity.location, entity.customName ?: return, world)
        }
    }

    /**
     * Removes all the holograms
     */
    override fun remove(crate: Crate) {
        val holos = holograms[crate] ?: return

        holos.forEach { it.remove() }
        holos.clear()
    }

    /**
     * Spawns the hologram entity
     */
    private fun spawnEntity(crate: Crate, location: Location, line: String, world: World) {
        val hologram = NameEntity(plugin, line, "pet-crate", world)
        hologram.setLocation(location.x, location.y, location.z, 0f, 0f)

        world.addEntity(hologram)

        val holoList = holograms[crate]

        if (holoList != null) {
            holoList.add(hologram.bukkitEntity)
        } else {
            // Cast to prevent `Type inference failed`
            holograms[crate] = mutableListOf(hologram.bukkitEntity as Entity)
        }
    }

}