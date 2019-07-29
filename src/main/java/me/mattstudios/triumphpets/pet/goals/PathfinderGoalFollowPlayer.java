package me.mattstudios.triumphpets.pet.goals;

import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.mattstudios.triumphpets.util.Utils.distance2d;
import static me.mattstudios.triumphpets.util.Utils.getSafeY;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {

    private double movementSpeed;
    private EntityInsentient petEntity;
    private Player owner;
    private NavigationAbstract navigation;

    private int controller = 0;

    public PathfinderGoalFollowPlayer(EntityInsentient petEntity, Player owner, double movementSpeed) {
        this.petEntity = petEntity;
        this.owner = owner;
        this.navigation = petEntity.getNavigation();
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void c() {
        Location location = owner.getLocation().clone();
        navigation.a(location.getX(), location.getY(), location.getZ(), movementSpeed);
    }

    @Override
    public boolean a() {
        Location location = owner.getLocation().clone();
        double dist = distance2d(location.getX(), petEntity.locX, location.getZ(), petEntity.locZ);

        if (dist > 7) {
            if (dist > 20) {
                petEntity.setPosition(location.getX(), getSafeY(location), location.getZ());
            }

            c();
        }

        controller = 0;

        return false;
    }

}
