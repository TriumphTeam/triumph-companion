package me.mattstudios.triumphpets.util

import com.cryptomorin.xseries.XSound
import org.bukkit.entity.Player

/**
 * @author Matt
 */
object Utils {

    /**
     * Plays the click sound
     */
    fun playClickSound(player: Player) {
        player.location.let { XSound.UI_BUTTON_CLICK.playSound(player, .3f, .5f) }
    }

}