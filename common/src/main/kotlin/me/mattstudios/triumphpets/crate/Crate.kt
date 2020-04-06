package me.mattstudios.triumphpets.crate

import org.bukkit.Location
import org.bukkit.block.BlockFace
import java.util.UUID


/**
 * @author Matt
 */
data class Crate(val uuid: UUID, val location: Location, val face: BlockFace) {

    /**
     * Checks whether or not the location is a crate
     */
    fun isCrate(location: Location): Boolean {
        return this.location == location
    }

}