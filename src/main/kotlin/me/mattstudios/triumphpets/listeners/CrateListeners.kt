package me.mattstudios.triumphpets.listeners

import me.mattstudios.mfgui.gui.components.ItemNBT
import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.block.BlockFace
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDeathEvent

/**
 * @author Matt
 */
class CrateListeners(plugin: TriumphPets) : Listener {

    private val crateController = plugin.petManager.crateController
    private val crateManager = plugin.petManager.crate

    /**
     * Checks for the crate creation with the item
     */
    @EventHandler
    fun BlockPlaceEvent.onCratePlace() {
        if (itemInHand.type != XMaterial.PLAYER_HEAD.parseMaterial()) return
        if (ItemNBT.getNBTTag(itemInHand, "pet-crate") != "pet-crate-item") return

        // Checks if the item is being placed on the top of a block
        if (blockAgainst.getFace(blockPlaced) != BlockFace.UP) {
            // TODO Custom message from file
            player.sendMessage("Crate can only be placed on top of a block")
            isCancelled = true
            return
        }

        // Creates the crate
        crateManager.setCrate(blockPlaced.location)
    }

    /**
     * Cancels if the crate is destroyed
     */
    @EventHandler
    fun BlockBreakEvent.onCrateDestroy() {
        if (block.type != XMaterial.PLAYER_HEAD.parseMaterial()) return
        if (!crateManager.isCrate(block.location)) return

        isCancelled = true
    }

    @EventHandler
    fun EntityDeathEvent.onCrateHologramDeath() {
        if (entityType != EntityType.ARMOR_STAND) return
        if (!crateController.isCrateEntity(entity)) return

        //crateController.respawnEntities()
    }

}