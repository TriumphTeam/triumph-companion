package me.mattstudios.triumphpets.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Utils {

    public static double distance(Vector loc1, Vector loc2) {
        return loc1.distance(loc2);
    }

    public static double distance2d(double x1, double x2, double z1, double z2) {
        return Math.hypot(Math.abs(x2 - x1), Math.abs(z2 - z1));
    }

    public static int getSafeY(Location location) {
        return Objects.requireNonNull(location.getWorld()).getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
    }

}
