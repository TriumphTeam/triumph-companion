package me.mattstudios.triumphpets.manager

import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.Database
import org.bukkit.Location
import org.bukkit.Material
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
    fun createCrate(location: Location) {
        val crate = Crate(UUID.randomUUID(), location)
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
     * Removes the crate
     */
    fun remove(location: Location) {
        val crate = crates.find { isCrate(location) } ?: return
        database.removeCrate(crate)
        location.block.type = Material.AIR

        // Removes the crate's
        crateController.remove(crate)
        crates.remove(crate)
    }

    /**
     * Initializes the crate
     */
    private fun initCrate(crate: Crate) {
        crateController.spawnCrateEntities(crate, mutableListOf("&aMultiple", "&bColored", "&cLines", "&dTest"))
    }

    /**
     * Removes all the holograms
     */
    fun removeAll() {
        crates.forEach { crateController.remove(it) }
    }


}