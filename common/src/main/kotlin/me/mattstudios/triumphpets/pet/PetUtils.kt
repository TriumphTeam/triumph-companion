package me.mattstudios.triumphpets.pet

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.math.abs
import kotlin.math.hypot

object PetUtils {

    /**
     * Calculates the distance between 2 entities in 3D space.
     *
     * @param loc1 The first location (Vector).
     * @param loc2 The second location (Vector).
     * @return The distance between them.
     */
    fun distance(loc1: Vector, loc2: Vector): Double {
        return loc1.distance(loc2)
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
    fun distance2d(x1: Double, x2: Double, z1: Double, z2: Double): Double {
        return hypot(abs(x2 - x1), abs(z2 - z1))
    }

    /**
     * Gets the safe location to TP to.
     *
     * @param location The location to verify.
     * @return The new safe Y coordinate.
     */
    fun getSafeY(location: Location, world: World): Int {
        return world.getHighestBlockYAt(location.blockX, location.blockZ)
    }

}