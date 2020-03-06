package me.mattstudios.triumphpets.listeners

import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityPortalEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent
import org.bukkit.event.player.PlayerQuitEvent


/**
 * @author Matt
 */
class PetListeners(private val plugin: TriumphPets) : Listener {

    private val petController = plugin.petManager.petController

    /**
     * Makes sure entities don't target the pet
     */
    @EventHandler
    fun EntityTargetLivingEntityEvent.onEntityTargetPet() {
        val target = target ?: return
        isCancelled = petController.isPet(target)
    }

    /**
     * Makes pet invincible
     */
    @EventHandler
    fun EntityDamageEvent.onPetDamage() {
        isCancelled = petController.isPet(entity) || petController.isPetComponent(entity)
    }

    /**
     * Removes the pet from the world when the player leaves
     */
    @EventHandler
    fun PlayerQuitEvent.onOwnerLeave() {
        petController.despawnPet(player)
    }

    /**
     * Makes so pet can't enter a portal, so it doesn't bug
     */
    @EventHandler
    fun EntityPortalEvent.onPetEnterPortal() {
        isCancelled = petController.isPet(entity) || petController.isPetComponent(entity)
    }

}