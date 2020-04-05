package me.mattstudios.triumphpets.commands.admin

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.mattstudios.mattcore.utils.NmsUtils.getNMSClass
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.CompleteFor
import me.mattstudios.mf.annotations.Optional
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.util.Items
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Rotatable
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator
import java.util.UUID


/**
 * @author Matt
 */
@Command("pet")
class CrateCommand(plugin: TriumphPets) : CommandBase() {

    private val crateManager = plugin.petManager.crateManager
    private val blockStateValues = BlockFace.values().toList().map { it.name }

    /**
     * Crate command that handles both Set and Unset of the Pet crate
     */
    @SubCommand("crate")
    fun crateSet(player: Player, @Values("#crate-type") type: String?, @Optional face: String?) {

        // TODO errors in this fun

        if (type == null) {
            player.sendMessage("Error here")
            return
        }

        val lookingBlock = getLookingBlock(player)

        if (type == "set") {
            val faceExists = !BlockFace.values().toList().map { it.name }.contains(face?.toUpperCase())
            val blockFace = if (face == null || faceExists) BlockFace.WEST_SOUTH_WEST else BlockFace.valueOf(face.toUpperCase())

            setCrate(lookingBlock, player, blockFace)
            return
        }

        unsetCrate(player)
    }

    /**
     * Completes the values for the crate command, adding values if player is setting and removing if unsetting
     */
    @CompleteFor("crate")
    fun complete(arguments: List<String>): List<String> {
        // Completes with the normal options
        if (arguments.size == 1) {
            return getTabValues(listOf("set", "unset"), arguments[0])
        }

        // Completes with the block face enum
        if (arguments.size == 2 && arguments[0] == "set") {
            return getTabValues(blockStateValues, arguments[1])
        }

        // In case no real value was introduced
        return emptyList()
    }

    /**
     * Sets the crate to the block
     */
    private fun setCrate(block: Block?, player: Player, face: BlockFace) {
        if (block == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        if (crateManager.isCrate(block.location)) {
            player.sendMessage("You already have a crate set!")
            return
        }

        val crateBlock = player.world.getBlockAt(block.location.clone().add(.0, 1.0, .0))

        if (crateBlock.type != Material.AIR) {
            player.sendMessage("Another error")
            return
        }

        crateBlock.type = Material.PLAYER_HEAD

        // Creates the game profile for the skull
        val profile = GameProfile(UUID.randomUUID(), null)
        profile.properties.put("textures", Property("textures", Items.CRATE_ITEM.texture))

        // Sets the skull texture
        setSkullTexture(getSkullTile(player.world, crateBlock), profile)

        // Sets the rotation of the block
        val data = crateBlock.blockData as Rotatable
        data.rotation = face
        crateBlock.blockData = data
        crateBlock.state.update(true)

        crateManager.createCrate(crateBlock.location)
    }

    /**
     * Removes the crate
     * Does not require the looking block
     */
    private fun unsetCrate(player: Player) {
        //crate.remove(player)
    }

    /**
     * Gets the block the player is looking at
     */
    private fun getLookingBlock(player: Player): Block? {
        val blockIterator = BlockIterator(player, 5)

        var lastBlock = blockIterator.next()

        while (blockIterator.hasNext()) {
            lastBlock = blockIterator.next()

            if (lastBlock.type == Material.AIR) continue

            break
        }

        return if (lastBlock.type == Material.AIR) null else lastBlock
    }

    /**
     * Gets the values to tab complete
     */
    private fun getTabValues(defaultValues: List<String>, argument: String): List<String> {
        val returnValues = mutableListOf<String>()

        // Check if player is typing or not
        if ("" != argument) {

            // Makes the tab completion reduce while typing
            for (value in defaultValues) {
                if (!value.toLowerCase().startsWith(argument.toLowerCase())) continue
                returnValues.add(value)
            }

        } else {
            return defaultValues
        }

        return returnValues
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

    /**
     * Reflection to get the skull tile from a block
     */
    private fun getSkullTile(world: World, block: Block): Any {
        val nmsWorld = getNmsWorld(world)
        return nmsWorld.javaClass.getMethod("getTileEntity", getNMSClass("BlockPosition")).invoke(nmsWorld, getBlockPosition(block))
    }

    /**
     * Reflection to set the skull texture to the block
     */
    private fun setSkullTexture(skullTile: Any, profile: GameProfile) {
        skullTile.javaClass.getMethod("setGameProfile", GameProfile::class.java).invoke(skullTile, profile)
    }
}