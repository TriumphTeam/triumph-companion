package me.mattstudios.triumphpets.commands.player

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.Particle
import org.bukkit.entity.Player

/**
 * @author Matt
 */
@Command("pets")
class PetsCommand(private val plugin: TriumphPets) : CommandBase() {

    @Default
    fun pets(player: Player) {
        //player.sendMessage(plugin.petManager.dataManager.getPets(player).toString())
    }

}