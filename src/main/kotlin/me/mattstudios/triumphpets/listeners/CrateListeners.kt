package me.mattstudios.triumphpets.listeners

import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author Matt
 */
class CrateListeners(plugin: TriumphPets) : Listener {

    private val crateController = plugin.petManager.crateController
    private val crateManager = plugin.petManager.crate

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
        val block = clickedBlock ?: return

        if (block.type != XMaterial.PLAYER_HEAD.parseMaterial()) return
        if (!crateManager.isCrate(block.location)) return

        println("opening")
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