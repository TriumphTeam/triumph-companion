package me.mattstudios.triumphpets.listeners;

import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PetListener implements Listener {

    private TriumphPets plugin;

    public PetListener(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWolfTargetPet(EntityTargetLivingEntityEvent event) {
        if (!event.getEntityType().equals(EntityType.WOLF)) return;
        if (event.getTarget() == null) return;
        if (plugin.getPetController().isPetEntity(event.getTarget().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPetDamage(EntityDamageEvent event) {
        if (plugin.getPetController().isPetEntity(event.getEntity().getUniqueId())) event.setCancelled(true);
    }

}

