package me.mattstudios.triumphpets.util;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Utils {

    public static final String TAG = "&f[&3Triumph&bPets&f]&r ";

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

    /**
     * Encodes the item into a storable string.
     *
     * @param itemStack The item to encode.
     * @return A string with the encoded item.
     */
    public static String encodeItem(ItemStack itemStack) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.set("item", itemStack);
        return new String(Base64.encodeBase64(yamlConfiguration.saveToString().getBytes()));
    }

    /**
     * Decodes the string and turns it into an item.
     *
     * @param encodedItem The encoded string to turn into an item.
     * @return The new item.
     */
    public static ItemStack decodeItem(String encodedItem) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        try {
            yamlConfiguration.loadFromString(new String(Base64.decodeBase64(encodedItem)));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return yamlConfiguration.getItemStack("item");
    }
}
