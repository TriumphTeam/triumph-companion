package me.mattstudios.triumphpets.crate.effects

import me.mattstudios.triumphpets.crate.effects.base.BaseEffect
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author Matt
 */
class Orbit(location: Location) : BaseEffect(1) {

    private val world = location.world
    private val crateLocation = location.clone().add(.5, .25, .5)

    private var position = 0.0

    private val radius = .5

    /**
     * Spawns white particles in random locations around the Egg
     */
    override fun run() {
        if (world == null) return

        position += PI / 10

        val x = cos(position) * radius
        val z = sin(position) * radius

        world.spawnParticle(Particle.REDSTONE, crateLocation.clone().add(x, z, z), 1, .0, .0, .0, .0, Particle.DustOptions(Color.WHITE, .5f))

        if (position >= PI * 2) position = 0.0
    }

}