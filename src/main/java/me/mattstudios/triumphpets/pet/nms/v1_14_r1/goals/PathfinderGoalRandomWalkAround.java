package me.mattstudios.triumphpets.pet.nms.v1_14_r1.goals;

import me.mattstudios.triumphpets.pet.components.PetMemory;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.Random;

import static me.mattstudios.triumphpets.util.Utils.getSafeY;

public class PathfinderGoalRandomWalkAround extends PathfinderGoal {

    private double movementSpeed;
    private EntityInsentient petEntity;
    private NavigationAbstract navigation;
    private PetMemory petMemory;

    private Random random;
    private int chance;
    private int controller;

    public PathfinderGoalRandomWalkAround(EntityInsentient petEntity, PetMemory petMemory, double movementSpeed) {
        this.petEntity = petEntity;
        this.navigation = petEntity.getNavigation();
        this.movementSpeed = movementSpeed;
        this.petMemory = petMemory;

        chance = 15;
        controller = 0;
        random = new Random();
    }

    @Override
    public boolean a() {

        if (petMemory.isTracking()) return false;

        // Makes it run only once every 2 seconds.
        if (!shouldRun()) return false;

        if ((random.nextInt(99) + 1) >= chance) return false;

        moveAround();

        return true;
    }

    /**
     * Moves to a location between 10 and -10 blocks away from current location.
     */
    private void moveAround() {

        int x = random.nextInt(20) - 10;
        int z = random.nextInt(20) - 10;

        Entity petEntityBukkit = petEntity.getBukkitEntity();
        Block block = petEntityBukkit.getWorld().getBlockAt(petEntityBukkit.getLocation().clone().add(x, 0, z));

        navigation.a(block.getX(), getSafeY(block.getLocation()), block.getZ(), movementSpeed);

    }

    private boolean shouldRun() {
        if (controller <= 20) {
            controller++;
            return false;
        }
        controller = 0;
        return true;
    }

}
