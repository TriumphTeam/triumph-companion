package me.mattstudios.triumphpets.pet.v1_15

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.PetController
import me.mattstudios.triumphpets.pet.components.PetNameEntity
import me.mattstudios.triumphpets.pet.v1_15.pets.PetFox
import net.minecraft.server.v1_15_R1.EntityFox
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.entity.Player


/**
 * @author Matt
 */
class EntityController(private val plugin: MattPlugin) : PetController {

    // List with spawned entities
    private val spawnedPets = mutableSetOf<Pet>()

    /**
     * Checks weather or not the entity that is being tracked is a pet or not.
     */
    override fun isPet(entity: Entity): Boolean {
        return (entity as CraftEntity).handle is Pet
    }

    /**
     * Checks weather or not the entity that is being tracked is a pet or not.
     */
    override fun isPetComponent(entity: Entity): Boolean {
        return (entity as CraftEntity).handle is PetNameEntity
    }

    /**
     * Spawns a pet in the world.
     */
    override fun spawnPet(location: Location, player: Player) {
        val petFox = PetFox(plugin, (player.world as CraftWorld).handle, player, "&cFoxy", true, EntityFox.Type.SNOW)
        petFox.setPositionRotation(location.x, location.y, location.z, location.yaw, location.pitch)
        (player.world as CraftWorld).handle.addEntity(petFox)
        spawnedPets.add(petFox)
    }

    /**
     * Removes all the teams created in process and all the pet entities spawned.
     */
    override fun removeAll() {
        for (pet in spawnedPets) {
            pet.getEntity().remove()
        }
    }

}