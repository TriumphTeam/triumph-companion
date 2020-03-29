package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator

/**
 * @author Matt
 */
@Command("pet")
class CrateCommand(plugin: TriumphPets) : CommandBase() {

    private val crateController = plugin.petManager.crateController

    @SubCommand("crate")
    fun crateSet(player: Player, @Values("#crate-type") type: String?) {

        // TODO errors in this fun

        if (type == null) {
            player.sendMessage("Error here")
            return
        }

        val lookingBlock = getLookingBlock(player)

        if ("set" == type) {
            setCrate(lookingBlock, player)
            return
        }

        unsetCrate(lookingBlock, player)

        // TODO Will add the crate item to the player inventory
        /*val crateItem = setNBTTag(ItemBuilder(Items.CRATE_ITEM.item).setName("Crate Item").build(), "pet-crate", "pet-crate-item")
        player.inventory.addItem(crateItem)*/
    }

    private fun setCrate(block: Block?,  player: Player) {
        // Setting the crate here
    }

    private fun unsetCrate(block: Block?, player: Player) {
        if (block == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        block.type = Material.AIR
        crateController.remove()
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

        return lastBlock
    }

}