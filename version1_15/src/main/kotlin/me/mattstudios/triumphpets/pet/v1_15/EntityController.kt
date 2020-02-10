package me.mattstudios.triumphpets.pet.v1_15

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet.PetManager
import me.mattstudios.triumphpets.pet.v1_15.pets.PetFox
import net.minecraft.server.v1_15_R1.EntityFox
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.entity.Player


/**
 * @author Matt
 */
class EntityController : PetManager {

    fun spawnPet(plugin: MattPlugin, location: Location, player: Player) {
        val petFox = PetFox(plugin, (player.world as CraftWorld).handle, player, "&cFoxy", true, EntityFox.Type.SNOW)
        petFox.setPositionRotation(location.x, location.y, location.z, location.yaw, location.pitch)
        (player.world as CraftWorld).handle.addEntity(petFox)
        //scoreboardManager.manageTeamCollision(petFox.bukkitEntity, player)
        //spawnedPets.put(player.getUniqueId(), petFox)
    }

}