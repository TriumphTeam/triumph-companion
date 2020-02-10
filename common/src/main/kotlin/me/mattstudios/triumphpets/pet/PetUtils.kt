package me.mattstudios.triumphpets.pet

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.math.abs
import kotlin.math.hypot

object PetUtils {

    var LOCALE = "en_US"


    /**
     * Calculates the distance between 2 entities in 3D space
     */
    fun distance(loc1: Vector, loc2: Vector): Double {
        return loc1.distance(loc2)
    }

    /**
     * Calculates the distance between 2 entities but in 2D space ignoring vertical space
     */
    fun distance2d(x1: Double, x2: Double, z1: Double, z2: Double): Double {
        return hypot(abs(x2 - x1), abs(z2 - z1))
    }

    /**
     * Gets the safe location to TP to
     */
    fun getSafeY(location: Location, world: World): Int {
        return world.getHighestBlockYAt(location.blockX, location.blockZ)
    }

    /**
     * Gets the safe location to TP to
     */
    fun getSafeY(x: Double, z: Double, world: World): Int {
        return world.getHighestBlockYAt(x.toInt(), z.toInt())
    }

}