package me.mattstudios.triumphpets.pet;

import org.bukkit.inventory.Inventory;

public interface PetEntity {

    void tick();

    Inventory getInventory();

}
