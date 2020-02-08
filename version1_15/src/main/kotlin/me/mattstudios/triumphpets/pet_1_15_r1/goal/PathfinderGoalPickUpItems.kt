package me.mattstudios.triumphpets.pet_1_15_r1.goal

import me.mattstudios.mattcore.utils.TimeUtils.getSecondsDifference
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.PetUtils.distance
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity
import org.bukkit.entity.Item
import org.bukkit.util.Vector


/**
 * @author Matt
 */
class PathfinderGoalPickUpItems(private val pet: Pet, private val entityInsentient: EntityInsentient, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val navigation = entityInsentient.navigation
    private val inventory = pet.getInventory()
    private val petMemory = pet.getMemory()

    private var trackedItem: Item? = null
    private var startTime: Long = 0

    private val PICK_DIST = 1.5
    private val SEARCH_DISTANCE = 15
    private val FORGET_TIME = 5
    private val ITEM_TRACK_TICKS = 20

    private var controller = 0

    /**
     * Main ticking class for the PathfinderGoal.
     */
    override fun a(): Boolean {
        getItemToTrack()
        pickCloseItem()

        followItem()

        if (trackedItem != null && trackedItem!!.isDead) trackedItem = null

        return true
    }

    /**
     * Picks up the items that are close.
     */
    private fun pickCloseItem() {
        for (foundEntity in entityInsentient.bukkitEntity.getNearbyEntities(PICK_DIST, PICK_DIST, PICK_DIST)) {
            if (foundEntity !is Item) continue
            pickItem(foundEntity)
        }
    }

    /**
     * Makes pet follow the closest item.
     */
    private fun followItem() {

        if (trackedItem == null || trackedItem!!.isDead || petMemory.isForgotten(trackedItem!!)) {
            resetTracker()
            return
        }

        if (petMemory.tracking && startTime != 0L && getSecondsDifference(startTime) >= FORGET_TIME) {
            petMemory.forgetItem(trackedItem!!)
            pet.getEntity().world.spawnParticle(Particle.SMOKE_NORMAL, pet.getEntity().location.x, pet.getEntity().location.y, pet.getEntity().location.z, 50, .5, .5, .5, 0.0)
        }

        if (!petMemory.tracking) startTracking()

        navigation.a((trackedItem as CraftEntity).handle, MOVEMENT_SPEED)
    }

    /**
     * Gets the closes item for the pet to track.
     */
    private fun getItemToTrack() {
        // makes it run only once every 1 second.
        if (!shouldRun()) return

        for (foundEntity in pet.getEntity().getNearbyEntities(SEARCH_DISTANCE.toDouble(), 5.0, SEARCH_DISTANCE.toDouble())) {
            if (foundEntity !is Item) continue

            if (petMemory.isForgotten(foundEntity)) continue
            if (trackedItem == null || trackedItem!!.isDead) {
                trackedItem = foundEntity
                continue
            }

            if (distance(foundEntity.location.toVector(), Vector(entityInsentient.locX(), entityInsentient.locY(), entityInsentient.locZ())) < distance(trackedItem!!.location.toVector(), Vector(entityInsentient.locX(), entityInsentient.locY(), entityInsentient.locZ()))) {
                trackedItem = foundEntity
            }
        }
    }

    /**
     * Picks the item given to it.
     *
     * @param item The item to pick up.
     */
    private fun pickItem(item: Item) {
        //val event = PetPickUpItemEvent(PetType.PET_FOX_SNOW, item.itemStack, owner)
        //Bukkit.getPluginManager().callEvent(event)
        /*if (event.isCancelled()) {
            forget(item)
            return
        }*/
        item.world.playSound(item.location, Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f)
        inventory.addItem(item)
        item.remove()
    }


    /**
     * Starts tracking the item.
     */
    private fun startTracking() {
        petMemory.tracking = true
        startTime = System.currentTimeMillis()
    }

    /**
     * Resets the item tracker to track a new one.
     */
    private fun resetTracker() {
        trackedItem = null
        petMemory.tracking = false
        startTime = 0
    }

    private fun shouldRun(): Boolean {
        if (controller <= ITEM_TRACK_TICKS) {
            controller++
            return false
        }

        controller = 0
        return true
    }

}