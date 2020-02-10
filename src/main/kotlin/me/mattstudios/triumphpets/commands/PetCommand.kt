package me.mattstudios.triumphpets.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import org.bukkit.entity.Player

/**
 * @author Matt
 */
@Command("pet")
class PetCommand(private val plugin: TriumphPets) : CommandBase() {


    @Default
    fun help(player: Player) {

        plugin.petManager.petController.spawnPet(player.location, player)

    }

}