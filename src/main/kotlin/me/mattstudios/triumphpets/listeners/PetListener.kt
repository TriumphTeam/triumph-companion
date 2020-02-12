package me.mattstudios.triumphpets.listeners

import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent


/**
 * @author Matt
 */
class PetListener(private val plugin: TriumphPets) : Listener {

    /**
     * Makes sure entities don't target the pet
     */
    @EventHandler
    fun EntityTargetLivingEntityEvent.onEntityTargetPet() {
        val target = target ?: return
        isCancelled = plugin.petManager.petController.isPet(target)
    }

    /**
     * Makes pet invincible
     */
    @EventHandler
    fun EntityDamageEvent.onPetDamage() {
        isCancelled = plugin.petManager.petController.isPet(entity)
    }

}