package me.mattstudios.triumphpets.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    public static String encodeInventory(Inventory inventory) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.set("inventory", inventory.getContents());
        return new String(Base64.encodeBase64(yamlConfiguration.saveToString().getBytes()));
    }

    public static Inventory decodeInventory(Player player, String encodedItem) {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        try {
            yamlConfiguration.loadFromString(new String(Base64.decodeBase64(encodedItem)));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        ItemStack[] content = ((List<ItemStack>) yamlConfiguration.get("inventory")).toArray(new ItemStack[0]);

        Inventory inventory = Bukkit.getServer().createInventory(player, 9);
        inventory.setContents(content);

        return inventory;
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

    public static ItemStack getCustomSkull(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field profileField;

        try {
            assert headMeta != null;
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(headMeta);

        return head;
    }
}
