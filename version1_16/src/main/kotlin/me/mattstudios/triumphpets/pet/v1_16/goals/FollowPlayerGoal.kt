package me.mattstudios.triumphpets.pet.v1_16.goals

import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.v1_16.func.distance
import me.mattstudios.triumphpets.pet.v1_16.func.walkTo
import net.minecraft.server.v1_16_R1.EntityInsentient
import net.minecraft.server.v1_16_R1.PathfinderGoal


/**
 * @author Matt
 */
internal class FollowPlayerGoal(
        private val petInsentient: EntityInsentient,
        private val petConfig: PetConfig,
        private val movementSpeed: Double
) : PathfinderGoal() {

    private val pet = petInsentient as Pet

    private val petMemory = pet.petMemory
    private val owner = pet.petOwner

    private val navigation = petInsentient.navigation

    private var followDistance = petConfig[PetProperty.FOLLOW_DISTANCE]
    private val tpDistance = petConfig[PetProperty.TELEPORT_DISTANCE]

    private var controller = 0

    /**
     * Main ticking class for the PathfinderGoal
     */
    override fun a(): Boolean {

        if (!shouldRun()) return true

        // Allows the player to search from a little further
        followDistance = if (petMemory.isTracking) followDistance + 8 else petConfig[PetProperty.FOLLOW_DISTANCE]

        // Gets the distance between the player and the pet
        val ownerLocation = owner.location.clone()
        val distance = petInsentient.distance(ownerLocation)

        // Checks if distance is less than the follow distance
        if (distance < followDistance) return true

        // Checks if distance is less than the tp distance
        if (distance < tpDistance) {
            // Walks to player
            navigation.walkTo(owner, movementSpeed)
            return true
        }

        // Teleports to owner
        petInsentient.setPosition(ownerLocation.x, ownerLocation.y, ownerLocation.z)
        // TODO despawn pet if player goes too high
        return true

    }

    /**
     * Makes it run only once a second
     */
    private fun shouldRun(): Boolean {
        if (controller <= 10) {
            controller++
            return false
        }

        controller = 0
        return true
    }

}