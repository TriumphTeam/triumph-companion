package me.mattstudios.triumphpets.pet.v1_16.func

import net.minecraft.server.v1_16_R1.EntityInsentient
import net.minecraft.server.v1_16_R1.NavigationAbstract
import net.minecraft.server.v1_16_R1.PathfinderGoal
import net.minecraft.server.v1_16_R1.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.util.Vector
import net.minecraft.server.v1_16_R1.Entity as NmsEntity

/**
 * @author Matt
 */

/**
 * Easier distance calculations for entity insentient
 */
internal fun EntityInsentient.distance(location: Location) = location.toVector().distance(Vector(locX(), locY(), locZ()))
internal fun EntityInsentient.distanceSquared(entity: Entity) = entity.location.toVector().distanceSquared(Vector(locX(), locY(), locZ()))

/**
 *  Gets an nms entity from a bukkit entity
 */
internal fun Entity.asNmsEntity(): NmsEntity = (this as CraftEntity).handle

/**
 * Renaming for the NavigationAbstract "a" function to be easier to read
 */
internal fun NavigationAbstract.walkTo(player: Entity, movementSpeed: Double) = a((player as CraftEntity).handle, movementSpeed)
internal fun NavigationAbstract.walkTo(x: Double, y: Double, z: Double, movementSpeed: Double) = a(x, y, z, movementSpeed)

/**
 * Renaming for the PathfinderGoalSelector "a" function
 */
internal fun PathfinderGoalSelector.addGoal(priority: Int, goal: PathfinderGoal) = a(priority, goal)

