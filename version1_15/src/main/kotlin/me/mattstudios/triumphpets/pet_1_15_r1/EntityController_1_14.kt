package me.mattstudios.triumphpets.pet_1_15_r1

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet_1_15_r1.pets.PetFox
import net.minecraft.server.v1_15_R1.EntityFox
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.entity.Player


/**
 * @author Matt
 */
class EntityController_1_14 {

    fun spawnPet(plugin: MattPlugin, location: Location, player: Player) {
        val petFox = PetFox(plugin, (player.world as CraftWorld).handle, player, "&cFoxy", true, EntityFox.Type.SNOW)
        petFox.setPositionRotation(location.x, location.y, location.z, location.yaw, location.pitch)
        (player.world as CraftWorld).handle.addEntity(petFox)
        //scoreboardManager.manageTeamCollision(petFox.bukkitEntity, player)
        //spawnedPets.put(player.getUniqueId(), petFox)
    }

}