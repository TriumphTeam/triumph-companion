package me.mattstudios.triumphpets.pet;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface PetController {

    /**
     * Spawns a pet in the world.
     *
     * @param location The location the pet will appear.
     * @param player   The player to be the owner of the pet.
     */
    void spawnPet(Location location, Player player);

    /**
     * Checks weather or not the entity that is being tracked is a pet or not.
     *
     * @param entityUuid The entity UUID.
     * @return True if it is a pet, false if it is not.
     */
    boolean isPetEntity(UUID entityUuid);

    /**
     * Removes all the teams created in process and all the pet entities spawned.
     */
    void removeAll();

}
