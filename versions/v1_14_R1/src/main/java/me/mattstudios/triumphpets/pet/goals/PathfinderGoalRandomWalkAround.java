package me.mattstudios.triumphpets.pet.goals;

import me.mattstudios.triumphpets.pet.components.Memory;
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
    private Memory memory;

    private Random random;
    private int chance;
    private int controller;

    public PathfinderGoalRandomWalkAround(EntityInsentient petEntity, Memory memory, double movementSpeed) {
        this.petEntity = petEntity;
        this.navigation = petEntity.getNavigation();
        this.movementSpeed = movementSpeed;
        this.memory = memory;

        chance = 25;
        controller = 0;
        random = new Random();
    }

    @Override
    public boolean a() {

        if (memory.isTracking()) return false;

        // Makes it run only once every 2 seconds.
        if (controller == 0 || controller % 40 != 0) {
            controller++;
            return false;
        }
        controller = 0;

        if ((random.nextInt(99) + 1) >= chance) return false;

        moveAround();

        return false;
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


}
