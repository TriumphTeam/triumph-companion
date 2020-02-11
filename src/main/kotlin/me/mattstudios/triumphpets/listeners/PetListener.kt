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
    fun entityTargetPet(event: EntityTargetLivingEntityEvent) {
        val target = event.target ?: return
        if (plugin.petManager.petController.isPet(target)) event.isCancelled = true
    }

    /**
     * Makes pet invincible
     */
    @EventHandler
    fun petDamage(event: EntityDamageEvent) {
        if (plugin.petManager.petController.isPet(event.entity)) event.isCancelled = true
    }


}