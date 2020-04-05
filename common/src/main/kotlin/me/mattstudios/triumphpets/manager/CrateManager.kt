package me.mattstudios.triumphpets.manager

import me.mattstudios.triumphpets.crate.Crate
import me.mattstudios.triumphpets.crate.CrateController
import me.mattstudios.triumphpets.data.database.Database
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
class CrateManager(private val crateController: CrateController, private val database: Database) {

    private val crates = mutableSetOf<Crate>()

    fun loadCrate(crate: Crate) {
        crates.add(crate)
        initCrate(crate.location)
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
    fun remove(location: Location, player: Player) {
        location.block.type = Material.AIR
        //crateController.remove()
        player.sendMessage("Successfully removed")
    }

    /**
     * Initializes the crate
     */
    private fun initCrate(crateLocation: Location) {
        //crateController.spawnCrateEntities(crateLocation, mutableListOf("&aMultiple", "&bColored", "&cLines", "&dTest"))
    }

}