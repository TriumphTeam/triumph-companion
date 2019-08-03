package me.mattstudios.triumphpets.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Utils {

    /**
     * Calculates the distance between 2 entities in 3D space.
     *
     * @param loc1 The first location (Vector).
     * @param loc2 The second location (Vector).
     * @return The distance between them.
     */
    public static double distance(Vector loc1, Vector loc2) {
        return loc1.distance(loc2);
    }

    /**
     * Calculates the distance between 2 entities but in 2D space ignoring vertical space.
     *
     * @param x1 The X coordinate of entity 1.
     * @param x2 The X coordinate of entity 2.
     * @param z1 The Z coordinate of entity 1.
     * @param z2 The Z coordinate of entity 2.
     * @return The distance of the entities.
     */
    public static double distance2d(double x1, double x2, double z1, double z2) {
        return Math.hypot(Math.abs(x2 - x1), Math.abs(z2 - z1));
    }

    /**
     * Gets the safe location to TP to.
     *
     * @param location The location to verify.
     * @return The new safe Y coordinate.
     */
    public static int getSafeY(Location location) {
        return Objects.requireNonNull(location.getWorld()).getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
    }
}
