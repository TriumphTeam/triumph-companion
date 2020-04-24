package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.crate.componetents.CrateEffect
import me.mattstudios.triumphpets.crate.componetents.CrateEgg
import me.mattstudios.triumphpets.crate.componetents.EffectFactory
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

    fun updateEffect(crateEffect: CrateEffect) {
        if (crateEffect == this.crateEffect) return

        this.crateEffect = crateEffect
        effect.stop()
        effect = EffectFactory.createEffect(crateEffect, location)
        effect.start()
    }

}