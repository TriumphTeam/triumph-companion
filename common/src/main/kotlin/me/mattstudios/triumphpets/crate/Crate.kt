package me.mattstudios.triumphpets.crate

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player


/**
 * @author Matt
 */
class Crate(private val crateController: CrateController) {

    private var crateLocation: Location? = null

    /**
     * Sets the crate location and initializes it
     */
    fun setCrate(crateLocation: Location) {
        // TODO Add to database
        initCrate(crateLocation)
    }

    /**
     * Initializes the crate
     */
    private fun initCrate(crateLocation: Location) {
        this.crateLocation = crateLocation

        crateController.spawnCrateEntities(crateLocation, mutableListOf("&aMultiple", "&bColored", "&cLines", "&dTest"))
    }

    /**
     * Checks if the location is a crate of not
     */
    fun isCrate(location: Location): Boolean {
        return crateLocation == location
    }

    /**
     * Checks whether or not the crate is set or not
     */
    fun exists(): Boolean {
        return crateLocation != null
    }

    /**
     * Removes the crate
     */
    fun remove(player: Player) {
        val location = crateLocation

        if (location == null) {
            player.sendMessage("Error removing crate")
            return
        }

        val crateBlock = location.world?.getBlockAt(location) ?: return
        crateBlock.type = Material.AIR

        crateController.remove()
        crateLocation = null

        player.sendMessage("Successfully removed")
    }

}