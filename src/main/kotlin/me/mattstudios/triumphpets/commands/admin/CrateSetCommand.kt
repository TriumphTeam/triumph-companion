package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.mfgui.gui.components.ItemNBT.setNBTTag
import me.mattstudios.triumphpets.util.Items
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator

/**
 * @author Matt
 */
@Command("pet")
class CrateSetCommand : CommandBase() {

    @SubCommand("setcrate")
    fun crateSet(player: Player) {

        val lookingBlock = getLookingBlock(player)

        if (lookingBlock == null) {
            player.sendMessage("Temp message saying error")
            return
        }

        // TODO Will add the crate item to the player inventory
        val crateItem = setNBTTag(ItemBuilder(Items.CRATE_ITEM.item).setName("Crate Item").build(), "pet-crate", "pet-crate-item")
        player.inventory.addItem(crateItem)
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