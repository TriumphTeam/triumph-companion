package me.mattstudios.triumphpets.pet.nms.v1_14_r1;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetController;
import me.mattstudios.triumphpets.pet.PetEntity;
import me.mattstudios.triumphpets.pet.components.ScoreboardManager;
import me.mattstudios.triumphpets.pet.nms.v1_14_r1.pets.PetFox;
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

    private TriumphPets plugin;
    private Map<UUID, PetEntity> spawnedPets;
    private ScoreboardManager scoreboardManager;

    public EntityController_1_14_R1(TriumphPets plugin) {
        this.plugin = plugin;

        spawnedPets = new HashMap<>();
        scoreboardManager = new ScoreboardManager();
    }

    /**
     * Removes all the teams created in process and all the pet entities spawned.
     */
    public void removeAll() {
        scoreboardManager.removeAll();
        for (UUID uuid : spawnedPets.keySet()) {
            if (Bukkit.getServer().getEntity(spawnedPets.get(uuid).getEntity().getUniqueId()) == null) continue;
            Objects.requireNonNull(Bukkit.getServer().getEntity(spawnedPets.get(uuid).getEntity().getUniqueId())).remove();
        }
    }

    /**
     * Checks weather or not the entity that is being tracked is a pet or not.
     *
     * @param entityUuid The entity UUID.
     * @return True if it is a pet, false if it is not.
     */
    public boolean isPetEntity(UUID entityUuid) {
        if (spawnedPets == null || spawnedPets.isEmpty() || entityUuid == null) return false;

        for (UUID uuid : spawnedPets.keySet()) {
            if (spawnedPets.get(uuid).getEntity().getUniqueId().equals(entityUuid)) return true;
        }

        return false;
    }

    /**
     * Spawns a pet in the world.
     *
     * @param location The location the pet will appear.
     * @param player   The player to be the owner of the pet.
     */
    @Override
    public void spawnPet(Location location, Player player) {
        PetFox petFox = new PetFox(plugin, ((CraftWorld) player.getWorld()).getHandle(), player, "&cFoxy", true, EntityFox.Type.RED);
        petFox.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftWorld) player.getWorld()).getHandle().addEntity(petFox);
        scoreboardManager.manageTeamCollision(petFox.getBukkitEntity(), player);
        spawnedPets.put(player.getUniqueId(), petFox);
    }

}
