package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.manager.CrateManager
import me.mattstudios.triumphpets.util.Items
import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
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
            in 15..19 -> {
                eulerX += .1
                eulerZ += .1

                if (controller == 19) armorStand.equipment?.helmet = Items.CRATE_ITEM_CRACK_1.item
            }

            in 20..24 -> {
                eulerX -= .1
                eulerZ -= .1
            }

            25 -> {
                eulerX = 0.0
                eulerZ = 0.0
            }

            in 45..49 -> {
                eulerX -= .1
                eulerZ -= .1
            }

            in 50..54 -> {
                eulerX += .1
                eulerZ += .1
            }

            55 -> {
                eulerX = 0.0
                eulerZ = 0.0
            }
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
}