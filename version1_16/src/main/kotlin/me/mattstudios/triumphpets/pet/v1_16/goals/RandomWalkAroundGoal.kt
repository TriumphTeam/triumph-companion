package me.mattstudios.triumphpets.pet.v1_16.goals

import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetUtils.getSafeY
import me.mattstudios.triumphpets.pet.v1_16.func.walkTo
import net.minecraft.server.v1_16_R1.EntityInsentient
import net.minecraft.server.v1_16_R1.PathfinderGoal


/**
 * @author Matt
 */
internal class RandomWalkAroundGoal(
        petInsentient: EntityInsentient,
        petConfig: PetConfig,
        private val MOVEMENT_SPEED: Double
) : PathfinderGoal() {

    private val pet = petInsentient as Pet

    private val petMemory = pet.petMemory
    private val petInventory = pet.petInventory

    private val navigation = petInsentient.navigation

    private val chance = petConfig[PetProperty.WALK_AROUND_CHANCE]
    private var controller = 0

    /**
     * Path
     */
    override fun a(): Boolean {
        // Makes it run only once every 2 second
        if (!shouldRun()) return true
        if (petMemory.isTracking || petInventory.isOpen()) return true
        if ((1..100).random() >= chance) return true

        moveAround()

        return true
    }

    /**
     * Moves to a location between 10 and -10 blocks away from current location.
     */
    private fun moveAround() {
        val x = (-10..10).random().toDouble()
        val z = (-10..10).random().toDouble()

        val location = pet.entity.location.clone().add(x, 0.0, z)
        navigation.walkTo(location.x, getSafeY(location.x, location.z, pet.entity.world).toDouble(), location.z, MOVEMENT_SPEED)
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

}