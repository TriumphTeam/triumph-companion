package me.mattstudios.triumphpets.crate.effects

import me.mattstudios.triumphpets.crate.effects.base.BaseEffect
import org.bukkit.Location
import org.bukkit.Particle
import kotlin.random.Random

/**
 * @author Matt
 */
class Shimmer(location: Location) : BaseEffect(5) {

    private val world = location.world
    private val crateLocation = location.clone().add(.5, .5, .5)

    /**
     * Spawns white particles in random locations around the Egg
     */
    override fun run() {
        if (world == null) return

        val offSet = Random.nextDouble(-.05, .05)
        world.spawnParticle(Particle.FIREWORKS_SPARK, crateLocation, 0, offSet, .1, offSet, 1.0)
    }

}