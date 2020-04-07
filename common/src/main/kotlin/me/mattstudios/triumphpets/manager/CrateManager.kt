package me.mattstudios.triumphpets.manager

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.mattstudios.mattcore.utils.Task.later
import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.util.Items
import me.mattstudios.triumphpets.util.Utils.getSkullTile
import me.mattstudios.triumphpets.util.Utils.setSkullTexture
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Rotatable
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

    fun hideCrate(crate: Crate) {
        later(5) {
            crate.location.block.type = Material.AIR
            crateController.hide(crate)
        }
    }

    fun showCrate(crate: Crate) {
        setCrateBlock(crate)
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

            setCrateBlock(crate)
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

    /**
     * Sets the crate block back to normal
     */
    private fun setCrateBlock(crate: Crate) {
        val crateBlock = crate.location.block

        crateBlock.type = Material.PLAYER_HEAD

        // Creates the game profile for the skull
        val profile = GameProfile(UUID.randomUUID(), null)
        profile.properties.put("textures", Property("textures", Items.CRATE_ITEM.texture))

        // Sets the skull texture
        setSkullTexture(getSkullTile(crateBlock.world, crateBlock), profile)

        // Sets the rotation of the block
        val data = crateBlock.blockData as Rotatable
        data.rotation = crate.face
        crateBlock.blockData = data
        crateBlock.state.update(true)
    }
}