package me.mattstudios.triumphpets.listeners

import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.crate.CrateAnimation
import org.bukkit.Bukkit
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
class CrateListeners(private val plugin: TriumphPets) : Listener {

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

    /**
     * Event to open the crate
     */
    @EventHandler
    fun PlayerInteractEvent.onCrateOpen() {
        if (action != Action.RIGHT_CLICK_BLOCK) return
        if (hand != EquipmentSlot.HAND) return

        val block = clickedBlock ?: return

        if (block.type != XMaterial.PLAYER_HEAD.parseMaterial()) return

        val crate = crateManager.getCrate(block.location) ?: return

        val animationTask = CrateAnimation(player, crate, crateManager)
        animationTask.taskId = Bukkit.getScheduler().runTaskTimer(plugin, animationTask, 0, 1).taskId
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