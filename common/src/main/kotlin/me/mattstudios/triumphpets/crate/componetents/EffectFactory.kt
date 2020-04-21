package me.mattstudios.triumphpets.crate.componetents

import me.mattstudios.triumphpets.crate.effects.None
import me.mattstudios.triumphpets.crate.effects.Sparkle
import org.bukkit.Location

object EffectFactory {

    /**
     * Creates the effect given the ENUM
     */
    fun createEffect(crateEffect: CrateEffect, location: Location): Effect {

        return when(crateEffect) {
            CrateEffect.SPARKLE -> Sparkle(location)
            else -> None()
        }

    }

}