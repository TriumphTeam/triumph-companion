package me.mattstudios.triumphpets.listeners

import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

/**
 * @author Matt
 */
class CrateListeners(plugin: TriumphPets) : Listener {

    private val crateController = plugin.petManager.crateController
    private val crateManager = plugin.petManager.crateManager

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
    fun PlayerInteractEvent.onCrateOpen() {
        if (action != Action.RIGHT_CLICK_BLOCK) return
        if (hand != EquipmentSlot.HAND) return

        val block = clickedBlock ?: return

        if (block.type != XMaterial.PLAYER_HEAD.parseMaterial()) return
        if (!crateManager.isCrate(block.location)) return


        val crate = crateManager.getCrate(block.location) ?: return

        player.sendMessage(crate.uuid.toString())

        /*val armorStand = player.world.spawnEntity(block.location.clone().add(1.0, .0, .0), EntityType.ARMOR_STAND) as ArmorStand
        armorStand.equipment?.helmet = Items.CRATE_ITEM.item*/

    }

    /**
     * Checks if the hologram was killed and respawns it
     */
    @EventHandler
    fun EntityDeathEvent.onCrateHologramDeath() {
        if (entityType != EntityType.ARMOR_STAND) return
        if (!crateController.isCrateEntity(entity)) return

        crateController.respawnEntity(entity)
    }

}