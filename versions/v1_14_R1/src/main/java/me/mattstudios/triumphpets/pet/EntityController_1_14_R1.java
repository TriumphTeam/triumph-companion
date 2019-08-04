package me.mattstudios.triumphpets.pet;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.pets.PetFox;
import me.mattstudios.triumphpets.util.ScoreboardManager;
import net.minecraft.server.v1_14_R1.EntityFox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class EntityController_1_14_R1 implements PetController {

    TriumphPets pluigin;
    private Map<UUID, UUID> spawnedPets;
    private ScoreboardManager scoreboardManager;

    public EntityController_1_14_R1() {
        spawnedPets = new HashMap<>();
        scoreboardManager = new ScoreboardManager();
    }

    /**
     * Removes all the teams created in process and all the pet entities spawned.
     */
    public void removeAll() {
        scoreboardManager.removeAll();
        for (UUID uuid : spawnedPets.keySet()) {
            if (Bukkit.getServer().getEntity(spawnedPets.get(uuid)) == null) continue;
            Objects.requireNonNull(Bukkit.getServer().getEntity(spawnedPets.get(uuid))).remove();
        }
    }

    /**
     * Checks weather or not the entity that is being tracked is a pet or not.
     *
     * @param entityUuid The entity UUID.
     * @return True if it is a pet, false if it is not.
     */
    public boolean isPetEntity(UUID entityUuid) {
        if (spawnedPets == null || spawnedPets.isEmpty()) return false;
        return spawnedPets.containsValue(entityUuid);
    }

    /**
     * Spawns a pet in the world.
     *
     * @param location The location the pet will appear.
     * @param player   The player to be the owner of the pet.
     */
    @Override
    public void spawnPet(Location location, Player player) {
        PetFox petFox = new PetFox(((CraftWorld) player.getWorld()).getHandle(), player, "&cFoxy", true, EntityFox.Type.RED);
        petFox.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftWorld) player.getWorld()).getHandle().addEntity(petFox);
        scoreboardManager.manageTeamCollision(petFox.getBukkitEntity(), player);
        spawnedPets.put(player.getUniqueId(), petFox.getUniqueID());
    }

}
