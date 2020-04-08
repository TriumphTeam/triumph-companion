package me.mattstudios.triumphpets.crate

import com.cryptomorin.xseries.XSound
import me.mattstudios.triumphpets.manager.CrateManager
import me.mattstudios.triumphpets.util.Items
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
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

    private var euler = 0.0

    private var wobbleDirection = (0..1).random()

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
                wobble()
                // Add first crack
                if (controller == 19) crack(Items.CRATE_ITEM_CRACK_1.item)
            }

            // Back to the beginning
            in 20..24 -> returnWobble()

            // Resets the rotation
            25 -> resetEgg()

            // Second wobble
            in 45..49 -> {
                returnWobble()
                // Second crack
                if (controller == 49) crack(Items.CRATE_ITEM_CRACK_2.item)
            }

            // Back to the beginning
            in 50..54 -> wobble()

            // Resets the rotation
            55 -> resetEgg()

            // Third wobble
            in 75..79 -> {
                wobble()
                // Add first crack
                if (controller == 79) crack(Items.CRATE_ITEM_CRACK_3.item)
            }

            // Back to the beginning
            in 80..84 -> returnWobble()

            // Resets the rotation
            85 -> resetEgg()

        }

        armorStand.headPose = EulerAngle(euler, 45.0, euler)

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
    private fun resetEgg() {
        euler = 0.0
    }

    /**
     * Increases the euler angle .1 x and z
     */
    private fun wobble() {
        if (wobbleDirection == 0) euler += .1
        else euler -= .1
    }

    /**
     * Decreases the euler angle .1 x and z
     */
    private fun returnWobble() {
        if (wobbleDirection == 0) euler -= .1
        else euler += .1
    }

    /**
     * Sets the armorstand head to the new item
     */
    private fun crack(head: ItemStack) {
        armorStand.equipment?.helmet = head

        // TODO add 1.12 sound - Furnace I guess
        XSound.ENTITY_TURTLE_EGG_CRACK.playSound(crate.location)

        player.world.spawnParticle(Particle.BLOCK_DUST, crate.location.clone().add(.5, .5, .5), 5, .2, .2, .2, .0, Material.WHITE_CONCRETE.createBlockData())
    }
}