package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetUtils.distance
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.NavigationAbstract
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer
import org.bukkit.util.Vector


/**
 * @author Matt
 */
class FollowPlayerGoal(pet: Pet, private val petInsentient: EntityInsentient, private val petConfig: PetConfig, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val petMemory = pet.getMemory()
    private val owner = pet.getPetOwner()

    private val navigation: NavigationAbstract = petInsentient.navigation

    private var followDistance = petConfig[PetProperty.FOLLOW_DISTANCE]
    private val tpDistance = petConfig[PetProperty.TELEPORT_DISTANCE]

    private var controller = 0

    /**
     * Main ticking class for the PathfinderGoal
     */
    override fun a(): Boolean {

        if (!shouldRun()) return true

        petInsentient.bukkitEntity

        // Allows the player to search from a little further
        followDistance = if (petMemory.isTracking) followDistance + 8 else petConfig[PetProperty.FOLLOW_DISTANCE]

        // Gets the distance between the player and the pet
        val location = owner.location.clone()
        val petLocation = Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ())
        val distance: Double = distance(location.toVector(), petLocation)

        // Checks if distance is less than the follow distance
        if (distance < followDistance) return true

        // Checks if distance is less than the tp distance
        if (distance < tpDistance) {
            // Walks to player
            navigation.a((owner as CraftPlayer).handle, MOVEMENT_SPEED)
            return true
        }

        // Teleports to owner
        petInsentient.setPosition(location.x, location.y, location.z)
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