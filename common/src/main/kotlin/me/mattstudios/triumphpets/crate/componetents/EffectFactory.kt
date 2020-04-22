package me.mattstudios.triumphpets.crate.componetents

import me.mattstudios.triumphpets.crate.effects.None
import me.mattstudios.triumphpets.crate.effects.Rings
import me.mattstudios.triumphpets.crate.effects.Shimmer
import me.mattstudios.triumphpets.crate.effects.Sparkle
import me.mattstudios.triumphpets.crate.effects.Spiral
import org.bukkit.Location

object EffectFactory {

    /**
     * Creates the effect given the ENUM
     */
    fun createEffect(crateEffect: CrateEffect, location: Location): Effect {

        return when(crateEffect) {
            CrateEffect.SPARKLE -> Sparkle(location)
            CrateEffect.SHIMMER -> Shimmer(location)
            CrateEffect.SPIRAL -> Spiral(location)
            CrateEffect.RINGS -> Rings(location)
            else -> None()
        }

    }

}