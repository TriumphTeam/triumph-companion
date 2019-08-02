package me.mattstudios.triumphpets.pet.goals;

import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static me.mattstudios.triumphpets.util.Utils.distance2d;
import static me.mattstudios.triumphpets.util.Utils.getSafeY;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {

    private double movementSpeed;
    private EntityInsentient petEntity;
    private Player owner;
    private NavigationAbstract navigation;

    public PathfinderGoalFollowPlayer(EntityInsentient petEntity, Player owner, double movementSpeed) {
        this.petEntity = petEntity;
        this.owner = owner;
        this.navigation = petEntity.getNavigation();
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void c() {
        Location location = owner.getLocation().clone();

        if (petEntity.isInWater()) petEntity.setSwimming(true);
        else petEntity.setSwimming(false);

        navigation.a(((CraftPlayer) owner).getHandle(), movementSpeed);
    }

    @Override
    public boolean a() {
        Location location = owner.getLocation().clone();
        double dist = distance2d(location.getX(), petEntity.locX, location.getZ(), petEntity.locZ);

        if (dist > 9) {
            if (dist > 20) {
                petEntity.setPosition(location.getX(), getSafeY(location), location.getZ());
            }

            c();
        }

        return false;
    }

}
