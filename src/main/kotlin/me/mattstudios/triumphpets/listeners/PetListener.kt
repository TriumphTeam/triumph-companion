package me.mattstudios.triumphpets.listeners

import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent


/**
 * @author Matt
 */
class PetListener(private val plugin: TriumphPets) : Listener {

    @EventHandler
    fun onWolfTargetPet(event: EntityTargetLivingEntityEvent) {
        if (event.entityType != EntityType.WOLF) return
        val target = event.target ?: return
        if (plugin.petManager.petController.isPet(target)) event.isCancelled = true
    }

    @EventHandler
    fun onPetDamage(event: EntityDamageEvent) {
        if (plugin.petManager.petController.isPet(event.entity)) event.isCancelled = true
    }


}