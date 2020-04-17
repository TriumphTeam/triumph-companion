package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.utils.Task.later
import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.util.Items
import me.mattstudios.triumphpets.util.Utils.setSkullBlock
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import java.util.UUID

/**
 * @author Matt
 */
class CrateManager(private val crateController: CrateController, private val database: Database) {

    private val crates = mutableSetOf<Crate>()

    /**
     * Loads the crate in from new or from the database
     */
    fun loadCrate(crate: Crate) {
        crates.add(crate)
        initCrate(crate)
    }

    /**
     * Sets the crate location and initializes it
     */
    fun createCrate(location: Location, face: BlockFace) {
        val crate = Crate(UUID.randomUUID(), location, face)
        database.insertCrate(crate)
        loadCrate(crate)
    }

    /**
     * Checks if the location is a crate of not
     */
    fun isCrate(location: Location): Boolean {
        return crates.any { it.isCrate(location) }
    }

    /**
     * Gets a grate based on the location
     */
    fun getCrate(location: Location): Crate? {
        return crates.find { it.isCrate(location) }
    }

    /**
     * Hides the crate's block / holograms
     */
    fun hideCrate(crate: Crate) {
        later(2) {
            crate.location.block.type = Material.AIR
            crateController.hide(crate)
        }
    }

    /**
     * Shows the crate block / holograms back
     */
    fun showCrate(crate: Crate) {
        setSkullBlock(crate.location, crate.face, Items.CRATE_ITEM_RED.texture)
        crateController.show(crate)
    }

    /**
     * Removes the crate
     */
    fun remove(location: Location) {
        val crate = getCrate(location) ?: return

        // Removes the crate's
        crateController.remove(crate)
        database.removeCrate(crate)
        crates.remove(crate)

        // Removes the player head
        location.block.type = Material.AIR
    }

    /**
     * Check if the crate block was removed for no reason
     */
    fun checkStartupMissing() {
        for (crate in crates) {
            val crateBlock = crate.location.block

            if (crateBlock.type == XMaterial.PLAYER_HEAD.parseMaterial()) continue

            setSkullBlock(crate.location, crate.face, Items.CRATE_ITEM_BLUE.texture)
        }
    }

    /**
     * Removes all the holograms
     */
    fun removeAll() {
        crates.forEach { crateController.remove(it) }
    }

    /**
     * Initializes the crate
     */
    private fun initCrate(crate: Crate) {
        crateController.spawnCrateEntities(crate, listOf("&3Pet &cCrate", "&7Click to open!"))
    }
}