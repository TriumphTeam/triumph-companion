package me.mattstudios.triumphpets.pet;

import me.mattstudios.triumphpets.pet.components.PetInventory;
import me.mattstudios.triumphpets.pet.components.PetMemory;
import org.bukkit.entity.Entity;

public interface PetEntity {

    void setName(String name);

    String getName();

    PetInventory getPetInventory();

    PetMemory getPetMemory();

    Entity getEntity();

}
