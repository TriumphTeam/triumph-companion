package me.mattstudios.triumphpets.pet;

import me.mattstudios.triumphpets.pet.pets.PetFox;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Player;

public class EntityController_1_14_R1 implements PetController {


    @Override
    public void spawnPet(Location loc, Player player) {
        PetFox petFox = new PetFox(((CraftWorld) player.getWorld()).getHandle(), player);
        petFox.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld) player.getWorld()).getHandle().addEntity(petFox);
    }
}
