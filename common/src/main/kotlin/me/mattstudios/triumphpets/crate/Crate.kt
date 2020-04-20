package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.crate.componetents.CrateEffect
import me.mattstudios.triumphpets.crate.componetents.CrateEgg
import org.bukkit.Location
import org.bukkit.block.Block
import java.util.UUID


/**
 * @author Matt
 */
data class Crate(
        val uuid: UUID,
        val location: Location,
        val crateEgg: CrateEgg,
        val crateEffect: CrateEffect,
        val blockUnder: Block
) {

    /**
     * Checks whether or not the location is a crate
     */
    fun isCrate(location: Location): Boolean {
        return this.location == location
    }

}