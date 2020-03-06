package me.mattstudios.triumphpets.listeners

import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.pet.PetPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityTargetLivingEntityEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Matt
 */
class PlayerListeners(plugin: TriumphPets) : Listener {

    private val dataManager = plugin.petManager.dataManager

    /**
     * Creates the pe player once the player joins the server
     */
    @EventHandler
    fun PlayerJoinEvent.onPlayerJoin() {
        // If player already exists, returns
        if (dataManager.getPetPlayer(player) != null) return

        dataManager.addPlayer(PetPlayer(player.uniqueId))
    }

}