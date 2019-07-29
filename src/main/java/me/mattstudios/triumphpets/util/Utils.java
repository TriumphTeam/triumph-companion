package me.mattstudios.triumphpets.util;

import org.bukkit.util.Vector;

public class Utils {

    public static double distance(Vector loc1, Vector loc2) {
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2) + Math.pow(loc1.getZ() - loc2.getZ(), 2));
    }

}
