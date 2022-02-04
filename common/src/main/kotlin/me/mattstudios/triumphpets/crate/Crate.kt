package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.crate.components.CrateEffect
import me.mattstudios.triumphpets.crate.components.CrateEgg
import me.mattstudios.triumphpets.crate.components.EffectFactory
import org.bukkit.Location
import org.bukkit.block.Block
import java.util.UUID


/**
 * @author Matt
 */
data class Crate(
        val uuid: UUID,
        val location: Location,
        var crateEgg: CrateEgg,
        var crateEffect: CrateEffect,
        // TODO Temporary
        var blockUnder: Block? = null
) {

    var effect = EffectFactory.createEffect(crateEffect, location)

    /**
     * Checks whether or not the location is a crate
     */
    fun isCrate(location: Location): Boolean {
        return this.location == location
    }

    /**
     * Updates the crate effect
     */
    fun updateEffect(crateEffect: CrateEffect) {
        if (crateEffect == this.crateEffect) return

        this.crateEffect = crateEffect
        effect.stop()
        effect = EffectFactory.createEffect(crateEffect, location)
        effect.start()
    }

}