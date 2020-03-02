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
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.util.Vector


/**
 * @author Matt
 */
class PickUpItemsGoal(private val pet: Pet, private val petInsentient: EntityInsentient, petConfig: PetConfig, private val MOVEMENT_SPEED: Double) : PathfinderGoal() {

    private val navigation = petInsentient.navigation
    private val petInventory = pet.getInventory()
    private val petMemory = pet.getMemory()

    private var target: Item? = null
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

        if (target?.isDead == true) target = null

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
        if (target == null || target?.isDead == true || petMemory.isForgotten(target)) {
            resetTracker()
            return
        }

        val currentTrackedItem = target ?: return

        if (petMemory.isTracking && startTime != 0L && getSecondsDifference(startTime) >= forgetTime) {
            petMemory.forgetItem(currentTrackedItem)
            pet.getEntity().world.spawnParticle(Particle.SMOKE_NORMAL, pet.getEntity().location.x, pet.getEntity().location.y, pet.getEntity().location.z, 50, .5, .5, .5, 0.0)
        }

        if (!petMemory.isTracking) startTracking()

        navigation.a(asNmsEntity(currentTrackedItem), MOVEMENT_SPEED)
    }

    /**
     * Gets the closes item for the pet to track
     */
    private fun getItemToTrack() {
        // makes it run only once every second
        if (!shouldRun()) return
        if (petInventory.isOpened()) return


        var itemToTrack: Item? = null

        for (foundEntity in pet.getEntity().getNearbyEntities(searchDistance, 7.5, searchDistance)) {
            if (foundEntity !is Item) continue
            if (petInventory.isFull(foundEntity.itemStack)) continue
            if (petMemory.isForgotten(foundEntity)) continue
            if (petMemory.isFiltered(foundEntity.itemStack.type)) continue

            if (itemToTrack == null || itemToTrack.isDead) {
                if (cantPath(foundEntity)) continue

                itemToTrack = foundEntity
                continue
            }

            if (PetUtils.distance(foundEntity.location.toVector(), Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ())) < PetUtils.distance(itemToTrack.location.toVector(), Vector(petInsentient.locX(), petInsentient.locY(), petInsentient.locZ()))) {
                if (cantPath(foundEntity)) continue

                itemToTrack = foundEntity
            }
        }

        setTarget(itemToTrack)

    }

    /**
     * Creates a path and checks if it is doable
     */
    private fun cantPath(entity: Entity): Boolean {
        val path = navigation.a(asNmsEntity(entity), 1)
        return path != null && !path.h()
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
        if (!petInventory.addItem(item)) target = null
    }


    /**
     * Starts tracking the item
     */
    private fun startTracking() {
        petMemory.isTracking = true
        startTime = System.currentTimeMillis()
    }

    /**
     * Gets the NMS entity out of the bukkit one
     */
    private fun asNmsEntity(entity: Entity): net.minecraft.server.v1_15_R1.Entity {
        return (entity as CraftEntity).handle
    }

    /***
     * Sets the target the pet should follow
     */
    private fun setTarget(item: Item?) {
        if (item == null) {
            target = item
            return
        }

        target = item
    }

    /**
     * Resets the item tracker to track a new one
     */
    private fun resetTracker() {
        target = null
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