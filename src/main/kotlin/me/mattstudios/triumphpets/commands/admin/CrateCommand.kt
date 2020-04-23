package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.crate.gui.CrateOptionsGui
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator

/**
 * @author Matt
 */
@Command("pet")
class CrateCommand(private val plugin: TriumphPets) : CommandBase() {

    private val crateManager = plugin.petManager.crateManager
    private val blockStateValues = BlockFace.values().toList().map { it.name }

    /**
     * Crate command that handles both Set and Unset of the Pet crate
     */
    @SubCommand("crate")
    fun crateSet(player: Player, @Values("#crate-type") type: String?) {

        // TODO errors in this fun

        if (type == null) {
            sendMessage("cmd.wrong.usage", player)
            return
        }

        val lookingBlock = getLookingBlock(player)

        when (type) {
            "set" -> {
                setCrate(lookingBlock, player)
                return
            }

            "edit" -> {
                editCrate(lookingBlock, player)
                return
            }

            else -> unsetCrate(lookingBlock, player)
        }

    }

    /**
     * Sets the crate to the block
     */
    private fun setCrate(block: Block?, player: Player) {
        if (block == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        if (crateManager.isCrate(block.location)) {
            player.sendMessage("You already have a crate set!")
            return
        }

        val crateBlock = player.world.getBlockAt(block.location.clone().add(.0, 1.0, .0))

        // Opens crate options
        CrateOptionsGui(plugin, crateManager, crateBlock.location, player)
    }

    /**
     * Sets the crate to the block
     */
    private fun editCrate(block: Block?, player: Player) {
        if (block == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        val crate = crateManager.getCrate(block.location)

        if (crate == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        // Opens crate options
        CrateOptionsGui(plugin, crateManager, crate.location, player, crate.crateEgg, crate.crateEffect)
    }

    /**
     * Removes the crate
     */
    private fun unsetCrate(block: Block?, player: Player) {
        if (block == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        if (!crateManager.isCrate(block.location)) {
            player.sendMessage("Temp message saying error")
            return
        }

        player.sendMessage(block.location.toString())

        crateManager.remove(block.location)
        player.sendMessage("crate removed")
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

}