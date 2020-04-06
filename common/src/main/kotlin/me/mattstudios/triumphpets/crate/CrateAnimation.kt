package me.mattstudios.triumphpets.crate

import me.mattstudios.triumphpets.manager.CrateManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit


/**
 * @author Matt
 */
class CrateAnimation(private val player: Player, private val crate: Crate, private val crateManager: CrateManager) : Runnable {

    //private var armorStand: ArmorStand = player.world.spawnEntity(crate.location.clone().add(.5, -.75, .5), EntityType.ARMOR_STAND) as ArmorStand
    private val start = System.currentTimeMillis()

    var taskId = 0

    init {
        crateManager.hideCrate(crate)

        /*armorStand.headPose = EulerAngle(0.0, 45.0, 0.0)
        armorStand.isSmall = true
        armorStand.equipment?.helmet = Items.CRATE_ITEM.item
        armorStand.isMarker = true
        armorStand.isVisible = false
        armorStand.setGravity(false)*/
    }

    override fun run() {
        if (getTimeSinceStart() >= 10) {
            cancel()
            return
        }

        player.sendMessage("Opening! - ${getTimeSinceStart()}")
    }

    /**
     * Cancels the task
     */
    private fun cancel() {
        crateManager.showCrate(crate)
        Bukkit.getScheduler().cancelTask(taskId)
    }

    /**
     * Gets the difference in seconds since start
     */
    private fun getTimeSinceStart(): Long {
        return TimeUnit.SECONDS.convert(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS)
    }
}