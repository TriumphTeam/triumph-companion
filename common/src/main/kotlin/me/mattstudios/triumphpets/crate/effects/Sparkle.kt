package me.mattstudios.triumphpets.crate.effects

import me.mattstudios.triumphpets.crate.effects.base.BaseEffect
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle

/**
 * @author Matt
 */
class Sparkle(location: Location) : BaseEffect(10) {

    private val world = location.world
    private val crateLocation = location.clone().add(.5, .25, .5)

    /**
     * Spawns white particles in random locations around the Egg
     */
    override fun run() {
        if (world == null) return

        world.spawnParticle(Particle.REDSTONE, crateLocation, 10, .3, .25, .3, .0,
                            Particle.DustOptions(Color.WHITE, .5f))

    }

}
