package me.mattstudios.triumphpets.crate

import org.bukkit.Location


/**
 * @author Matt
 */
class Crate(private val crateController: CrateController) {

    private var crateLocation: Location? = null

    fun setCrate(crateLocation: Location) {
        // TODO Add to database
        initCrate(crateLocation)
    }

    fun initCrate(crateLocation: Location) {
        this.crateLocation = crateLocation

        crateController.spawnCrateEntities(crateLocation, mutableListOf("&aMultiple", "&bColored", "&cLines", "&dTest"))
    }

    /**
     * Checks if the location is a crate of not
     */
    fun isCrate(location: Location): Boolean {
        return crateLocation == location
    }

}