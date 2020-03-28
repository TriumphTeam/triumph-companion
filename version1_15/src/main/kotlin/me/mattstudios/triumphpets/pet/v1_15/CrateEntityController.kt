package me.mattstudios.triumphpets.pet.v1_15

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.pet.components.PetNameEntity
import me.mattstudios.triumphpets.pet.v1_15.components.NameEntity
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity
import org.bukkit.entity.Entity

/**
 * @author Matt
 */
class CrateEntityController(private val plugin: MattPlugin) : CrateController {

    private var originalLocation: Location? = null
    private val originalLines = mutableListOf<String>()

    /**
     * Spawns the crate entities
     */
    override fun spawnCrateEntities(location: Location, lines: List<String>) {
        originalLines.clear()

        val world = (location.world as CraftWorld).handle
        val lineLocation = location.clone().add(.5, .5, .5)

        // Saves the entities data for the respawn
        originalLocation = lineLocation.clone()
        originalLines.addAll(lines.asReversed())

        // Spawns each line in their according location, reversed
        for (line in originalLines) {
            val nameEntity = NameEntity(plugin, line, "pet-crate", world)
            nameEntity.setLocation(lineLocation.x, lineLocation.y, lineLocation.z, 0f, 0f)

            world.addEntity(nameEntity)

            lineLocation.add(0.0, .25, 0.0)
        }
    }

    /**
     * Checks if the entity is a crate entity
     */
    override fun isCrateEntity(entity: Entity): Boolean {
        return (entity as CraftEntity).handle is PetNameEntity
    }

    override fun respawnEntities() {
        val lineLocation = originalLocation?.clone() ?: return
        val world = (lineLocation.world as CraftWorld).handle

        for (line in originalLines) {
            val nameEntity = NameEntity(plugin, line, "pet-crate", world)
            nameEntity.setLocation(lineLocation.x, lineLocation.y, lineLocation.z, 0f, 0f)

            world.addEntity(nameEntity)

            lineLocation.add(0.0, .25, 0.0)
        }
    }
}