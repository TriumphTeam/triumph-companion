package me.mattstudios.triumphpets.pet.goals;

import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {

    private double speed;
    private EntityInsentient entity;
    private Player player;
    private NavigationAbstract navigation;

    public PathfinderGoalFollowPlayer(EntityInsentient entity, Player player, double speed) {
        this.entity = entity;
        this.player = player;
        this.navigation = entity.getNavigation();
        this.speed = speed;
    }

    @Override
    public void c() {
        navigation.a(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), speed);
    }

    @Override
    public boolean a() {
        Location location = player.getLocation();
        double dist = Math.sqrt(Math.pow(location.getX() - entity.locX, 2) + Math.pow(location.getY() - entity.locY, 2) + Math.pow(location.getZ() - entity.locZ, 2));

        if (dist > 7) {
            if (dist > 20) {
                entity.setPosition(location.getX(), getSafeY(location), location.getZ());
            }

            c();
        }

        return false;
    }

    private int getSafeY(Location location) {
        return Objects.requireNonNull(location.getWorld()).getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
    }

}
