package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.PetUtils.getSafeY
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import java.util.Random


/**
 * @author Matt
 */
class PathfinderGoalRandomWalkAround(private val pet: Pet, private val petInsentient: EntityInsentient, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val petMemory = pet.getMemory()
    private val petInventory = pet.getInventory()

    private val navigation = petInsentient.navigation

    private val chance = 5
    private var controller = 0
    private val random: Random = Random()

    /**
     * Path
     */
    override fun a(): Boolean {
        // Makes it run only once every second
        if (!shouldRun()) return true
        if (petMemory.tracking || petInventory.isOpened()) return true
        if (randomize(1, 100) >= chance) return true

        moveAround()

        return true
    }

    /**
     * Moves to a location between 10 and -10 blocks away from current location.
     */
    private fun moveAround() {
        val x = randomize(-10, 10).toDouble()
        val z = randomize(-10, 10).toDouble()

        val petEntity = pet.getEntity()
        val location = petEntity.location.clone().add(x, 0.0, z)
        navigation.a(location.x, getSafeY(location.x, location.z, petEntity.world).toDouble(), location.z, MOVEMENT_SPEED)
    }

    /**
     * Makes it run every second
     */
    private fun shouldRun(): Boolean {
        if (controller <= 20) {
            controller++
            return false
        }

        controller = 0
        return true
    }

    /**
     * Randomizes a number between min and max
     */
    private fun randomize(min: Int, max: Int): Int {
        return random.nextInt((max - min) + 1) + min
    }

}