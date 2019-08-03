package me.mattstudios.triumphpets.pet.goals;

import me.mattstudios.triumphpets.pet.components.Memory;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static me.mattstudios.triumphpets.util.Utils.distance2d;
import static me.mattstudios.triumphpets.util.Utils.getSafeY;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {

    private final double MOVEMENT_SPEED;
    private EntityInsentient petEntity;
    private Player owner;
    private NavigationAbstract navigation;
    private Memory memory;

    private int followDistance;
    private int tpDistance;

    public PathfinderGoalFollowPlayer(EntityInsentient petEntity, Player owner, Memory memory, double MOVEMENT_SPEED) {
        this.petEntity = petEntity;
        this.owner = owner;
        this.navigation = petEntity.getNavigation();
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
        this.memory = memory;

        followDistance = 10;
        tpDistance = 20;
    }

    @Override
    public boolean a() {

        if (memory.isTracking()) followDistance = 15;
        else followDistance = 10;

        Location location = owner.getLocation().clone();
        double dist = distance2d(location.getX(), petEntity.locX, location.getZ(), petEntity.locZ);

        if (dist >= followDistance) {
            if (dist >= tpDistance) {
                petEntity.setPosition(location.getX(), getSafeY(location), location.getZ());
            }

            move();
        }

        return false;
    }

    private void move() {
        navigation.a(((CraftPlayer) owner).getHandle(), MOVEMENT_SPEED);
    }

}
