package me.mattstudios.triumphpets.pet_1_15_r1.goal

import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.PetUtils.distance2d
import me.mattstudios.triumphpets.pet.PetUtils.getSafeY
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.NavigationAbstract
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer


/**
 * @author Matt
 */
class PathfinderGoalFollowPlayer(pet: Pet, private val entityInsentient: EntityInsentient, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val petMemory = pet.getMemory()
    private val owner = pet.getOwner()

    private val navigation: NavigationAbstract  = entityInsentient.navigation
    private var followDistance = 7
    private val tpDistance = 20

    override fun a(): Boolean {

        followDistance = if (petMemory.tracking) 15 else 7

        val location: Location = owner.location.clone()
        val dist: Double = distance2d(location.x, entityInsentient.locX(), location.z, entityInsentient.locZ())

        if (dist >= followDistance) {
            if (dist >= tpDistance) {
                entityInsentient.setPosition(location.x, getSafeY(location, owner.world).toDouble(), location.z)
            }

            move()
        }

        return false
    }

    private fun move() {
        navigation.a((owner as CraftPlayer).handle, MOVEMENT_SPEED)
    }
}