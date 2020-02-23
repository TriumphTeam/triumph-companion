package me.mattstudios.triumphpets.pet.v1_15.goals

import me.mattstudios.mattcore.utils.TimeUtils.getSecondsDifference
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import me.mattstudios.triumphpets.events.PetPickUpItemEvent
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.utils.PetType
import me.mattstudios.triumphpets.pet.utils.PetUtils
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoal
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity
import org.bukkit.entity.Item
import org.bukkit.util.Vector


/**
 * @author Matt
 */
class PickUpItemsGoal(private val pet: Pet, private val petInsentient: EntityInsentient, petConfig: PetConfig, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val navigation = petInsentient.navigation
    private val petInventory = pet.getInventory()
    private val petMemory = pet.getMemory()

    private var trackedItem: Item? = null
    private var startTime: Long = 0

    private val pickDistance = petConfig[PetProperty.ITEM_PICK_DISTANCE]
    private val searchDistance = petConfig[PetProperty.ITEM_SEARCH_DISTANCE]
    private val forgetTime = petConfig[PetProperty.ITEM_FORGET_TIME]
    private val itemTrackTicks = petConfig[PetProperty.ITEM_TRACK_TICKS]

    private var controller = 0
    private var pickUpController = 0

    /**
     * Main ticking class for the PathfinderGoal
     */
    override fun a(): Boolean {
        getItemToTrack()
        pickCloseItem()

        followItem()

        if (trackedItem?.isDead == true) trackedItem = null

        return true
    }

    /**
     * Picks up the items that are close
     */
    private fun pickCloseItem() {
        if (!shouldPickUp()) return

        for (foundEntity in pet.getEntity().getNearbyEntities(pickDistance, pickDistance, pickDistance)) {
            if (foundEntity !is Item) continue
            pickItem(foundEntity)
        }
    }

    /**
     * Makes pet follow the closest item
     */
    private fun followItem() {

        // Checking if the item is null, dead, or forgotten
        if (trackedItem == null || trackedItem?.isDead == true || petMemory.isForgotten(trackedItem)) {
            resetTracker()
            return
        }

        val currentTrackedItem = trackedItem ?: return

        if (getSecondsDifference(startTime) >= forgetTime / 2) petInsentient.controllerJump.jump()

        if (petMemory.isTracking && startTime != 0L && getSecondsDifference(startTime) >= forgetTime) {
            petMemory.forgetItem(currentTrackedItem)
            pet.getEntity().world.spawnParticle(Particle.SMOKE_NORMAL, pet.getEntity().location.x, pet.getEntity().location.y, pet.getEntity().location.z, 50, .5, .5, .5, 0.0)
        }

        if (!petMemory.isTracking) startTracking()

        navigation.a((currentTrackedItem as CraftEntity).handle, MOVEMENT_SPEED)
    }

    /**
     * Gets the closes item for the pet to track
     */
    private fun getItemToTrack() {
        // makes it run only once every second
        if (!shouldRun()) return
        if (petInventory.isOpened()) return

        for (foundEntity in pet.getEntity().getNearbyEntities(searchDistance, 5.0, searchDistance)) {
            if (foundEntity !is Item) continue
            if (petInventory.isFull(foundEntity.itemStack)) continue
            if (petMemory.isForgotten(foundEntity)) continue
            if (petMemory.isFiltered(foundEntity.itemStack.type)) continue

            if (trackedItem == null || trackedItem?.isDead == true) {
                trackedItem = foundEntity
                continue
            }

            val currentTrackedItem = trackedItem ?: continue

            if (PetUtils.distance(foundEntity.location.toVector(), Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ())) < PetUtils.distance(currentTrackedItem.location.toVector(), Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ()))) {
                trackedItem = foundEntity
            }
        }
    }



    /**
     * Picks the item given to it
     */
    private fun pickItem(item: Item) {
        // Calls event
        val event = PetPickUpItemEvent(PetType.PET_FOX_SNOW, item.itemStack, pet.getOwner())
        Bukkit.getPluginManager().callEvent(event)

        if (event.isCancelled) {
            petMemory.forgetItem(item)
            return
        }

        // If the inventory is full can't pick up
        if (petInventory.isFull(item.itemStack)) return
        if (petMemory.isFiltered(item.itemStack.type)) return

        // plays pick up sound, adds item to the inventory, and removes the item entity
        item.world.playSound(item.location, Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f)
        if (!petInventory.addItem(item)) trackedItem = null
    }


    /**
     * Starts tracking the item
     */
    private fun startTracking() {
        petMemory.isTracking = true
        startTime = System.currentTimeMillis()
    }

    /**
     * Resets the item tracker to track a new one
     */
    private fun resetTracker() {
        trackedItem = null
        petMemory.isTracking = false
        startTime = 0
    }

    /**
     * Makes it run only once a second
     */
    private fun shouldRun(): Boolean {
        if (controller <= itemTrackTicks) {
            controller++
            return false
        }

        controller = 0
        return true
    }

    /**
     * Makes it run only once a second
     */
    private fun shouldPickUp(): Boolean {
        if (pickUpController <= 5) {
            pickUpController++
            return false
        }

        pickUpController = 0
        return true
    }

}