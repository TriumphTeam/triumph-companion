package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetUtils.distance2d
import me.mattstudios.triumphpets.pet.utils.PetUtils.getSafeY
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.NavigationAbstract
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer


/**
 * @author Matt
 */
class FollowPlayerGoal(pet: Pet, private val petInsentient: EntityInsentient,private val petConfig: PetConfig, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val petMemory = pet.getMemory()
    private val owner = pet.getOwner()

    private val navigation: NavigationAbstract  = petInsentient.navigation

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
        val location: Location = owner.location.clone()
        val dist: Double = distance2d(location.x, petInsentient.locX(), location.z, petInsentient.locZ())

        // If distance is bigger than follow distance it'll walk to owner
        // If distance is bigger than tp distance it'll tp to owner
        if (dist >= followDistance) {
            if (dist >= tpDistance) {
                petInsentient.setPosition(location.x, getSafeY(location, owner.world).toDouble(), location.z)
            }

            navigation.a((owner as CraftPlayer).handle, MOVEMENT_SPEED)
        }

        return true
    }

    /**
     * Makes it run only once a second
     */
    private fun shouldRun(): Boolean {
        if (controller <= 2) {
            controller++
            return false
        }

        controller = 0
        return true
    }

}