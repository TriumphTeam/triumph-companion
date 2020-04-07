package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.manager.CrateManager
import me.mattstudios.triumphpets.util.Items
import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.EulerAngle
import java.util.concurrent.TimeUnit


/**
 * @author Matt
 */
class CrateAnimation(private val player: Player, private val crate: Crate, private val crateManager: CrateManager) : Runnable {

    private var armorStand: ArmorStand = player.world.spawn(crate.location.clone().add(.5, -.70, .5), ArmorStand::class.java) {
        it.isSilent = true
        it.isVisible = false
    }
    private val start = System.currentTimeMillis()

    private var controller = 0

    private val eulerY = 45.0
    private var eulerX = 0.0
    private var eulerZ = 0.0

    var taskId = 0

    init {
        crateManager.hideCrate(crate)

        armorStand.headPose = EulerAngle(0.0, 45.0, 0.0)
        armorStand.isSmall = true
        armorStand.equipment?.helmet = Items.CRATE_ITEM.item
        armorStand.isMarker = true
        armorStand.setGravity(false)
    }

    override fun run() {
        if (getTimeSinceStart() >= 5) {
            cancel()
            return
        }

        when (controller) {
            // First wobble
            in 15..19 -> {
                increaseEuler()
                // Add first crack
                if (controller == 19) setHead(Items.CRATE_ITEM_CRACK_1.item)
            }

            // Back to the beginning
            in 20..24 -> decreaseEuler()

            // Resets the rotation
            25 -> resetEuler()

            // Second wobble
            in 45..49 -> {
                decreaseEuler()
                // Second crack
                if (controller == 49) setHead(Items.CRATE_ITEM_CRACK_2.item)
            }

            // Back to the beginning
            in 50..54 -> increaseEuler()

            // Resets the rotation
            55 -> resetEuler()
        }

        armorStand.headPose = EulerAngle(eulerX, eulerY, eulerZ)

        controller++
    }

    /**
     * Cancels the task
     */
    private fun cancel() {
        crateManager.showCrate(crate)
        armorStand.remove()
        Bukkit.getScheduler().cancelTask(taskId)
    }

    /**
     * Gets the difference in seconds since start
     */
    private fun getTimeSinceStart(): Long {
        return TimeUnit.SECONDS.convert(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS)
    }

    /**
     * Resets the euler angle back to 0 x and z
     */
    private fun resetEuler() {
        eulerX = 0.0
        eulerZ = 0.0
    }

    /**
     * Increases the euler angle .1 x and z
     */
    private fun increaseEuler() {
        eulerX += .1
        eulerZ += .1
    }

    /**
     * Decreases the euler angle .1 x and z
     */
    private fun decreaseEuler() {
        eulerX -= .1
        eulerZ -= .1
    }

    /**
     * Sets the armorstand head to the new item
     */
    private fun setHead(head: ItemStack) {
        armorStand.equipment?.helmet = head
    }
}