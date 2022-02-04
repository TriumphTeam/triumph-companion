package me.mattstudios.triumphpets.util

import com.cryptomorin.xseries.XSound
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.mattstudios.mattcore.utils.NmsUtils
import org.apache.commons.codec.binary.Base64
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Rotatable
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.UUID

/**
 * @author Matt
 */
object Utils {

    // Matches `world:x:y:z`
    private val regex = Regex("(?<world>[^:]*):(?<x>-?\\d+):(?<y>-?\\d+):(?<z>-?\\d+)")

    /**
     * Plays the click sound
     */
    fun playClickSound(player: Player) {
        player.location.let { XSound.UI_BUTTON_CLICK.playSound(player, .3f, .5f) }
    }

    /**
     * Gets the startup message for the plugin
     */
    fun getPluginStartup(): String {
        return if (isPaper()) "&3&l█▀█ █▀▀ ▀█▀ █▀\n" +
                "&3&l█▀▀ ██▄ ░█░ ▄█"
        else "&3&lTriumph Pets"
    }

    /**
     * Checks if the server is paper or Spigot
     */
    fun isPaper(): Boolean {
        val isPaper = try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager\$VersionData")
        } catch (e: ClassNotFoundException) {
            null
        }
        return isPaper != null
    }

    /**
     * Encodes the item into a storable string
     */
    fun encodeItem(itemStack: ItemStack): String {
        val yamlConfiguration = YamlConfiguration()
        yamlConfiguration["item"] = itemStack
        return String(Base64.encodeBase64(yamlConfiguration.saveToString().toByteArray()))
    }

    /**
     * Decodes the string and turns it into an item
     */
    fun decodeItem(encodedItem: String): ItemStack {
        val yamlConfiguration = YamlConfiguration()
        try {
            yamlConfiguration.loadFromString(String(Base64.decodeBase64(encodedItem)))
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
        val set = mutableSetOf<Location>()
        return yamlConfiguration.getItemStack("item") ?: ItemStack(Material.AIR)
    }

    /**
     * Encodes the entire inventory into Base 64
     */
    fun encodeInventory(inventory: Inventory): String {
        val yamlConfiguration = YamlConfiguration()
        yamlConfiguration["inventory"] = inventory.contents
        return String(Base64.encodeBase64(yamlConfiguration.saveToString().toByteArray()))
    }

    /**
     * Decodes the given inventory
     */
    fun decodeInventory(player: Player, encodedItem: String): Inventory {
        val yamlConfiguration = YamlConfiguration()
        try {
            yamlConfiguration.loadFromString(String(Base64.decodeBase64(encodedItem)))
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }

        // TODO NEEDS TESTING AND CHANGING
        @Suppress("UNCHECKED_CAST")
        val content: List<ItemStack> = yamlConfiguration["inventory"] as List<ItemStack>

        val inventory = Bukkit.getServer().createInventory(player, 9)
        inventory.contents = content.toTypedArray()
        Bukkit.getWorlds()
        return inventory
    }

    /**
     * Turns a location into a String
     */
    fun blockLocationToString(location: Location): String {
        return StringBuilder(32).append(location.world?.name)
                .append(':')
                .append(location.blockX)
                .append(':')
                .append(location.blockY)
                .append(':')
                .append(location.blockZ)
                .toString()
    }

    /**
     * Turns a string into a location
     */
    fun stringToBlockLocation(storedLocation: String): Location? {
        val (rWorld, rX, rY, rZ) = regex.matchEntire(storedLocation)?.destructured ?: return null
        val world = Bukkit.getWorld(rWorld) ?: return null

        val x = rX.toDoubleOrNull() ?: return null
        val y = rY.toDoubleOrNull() ?: return null
        val z = rZ.toDoubleOrNull() ?: return null

        return Location(world, x, y, z)
    }

    /**
     * Sets the crate block back to normal
     */
    fun Location.setBlockTexture(texture: String) {
        val crateBlock = block
        crateBlock.type = Material.PLAYER_HEAD

        // Creates the game profile for the skull
        val profile = GameProfile(UUID.randomUUID(), null)
        profile.properties.put("textures", Property("textures", texture))

        // Sets the skull texture
        setSkullTexture(getSkullTile(crateBlock.world, crateBlock), profile)

        // Sets the rotation of the block
        val data = crateBlock.blockData as Rotatable
        data.rotation = BlockFace.NORTH_WEST
        crateBlock.blockData = data
        crateBlock.state.update(true)
    }

    /**
     * Reflection to set the skull texture to the block
     */
    private fun setSkullTexture(skullTile: Any, profile: GameProfile) {
        skullTile.javaClass.getMethod("setGameProfile", GameProfile::class.java).invoke(skullTile, profile)
    }

    /**
     * Reflection to get the skull tile from a block
     */
    private fun getSkullTile(world: World, block: Block): Any {
        val nmsWorld = getNmsWorld(world)
        return nmsWorld.javaClass.getMethod("getTileEntity", NmsUtils.getNMSClass("BlockPosition")).invoke(nmsWorld, getBlockPosition(block))
    }

    /**
     * Reflection to get the NMS world from a bukkit world
     */
    private fun getNmsWorld(world: World): Any {
        return world.javaClass.getMethod("getHandle").invoke(world)
    }

    /**
     * Reflection to get the block position from a block
     */
    private fun getBlockPosition(block: Block): Any {
        return block.javaClass.getMethod("getPosition").invoke(block)
    }

}