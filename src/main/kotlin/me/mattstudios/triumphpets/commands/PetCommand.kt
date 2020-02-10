package me.mattstudios.triumphpets.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.pet.v1_15.EntityController
import org.bukkit.entity.Player

/**
 * @author Matt
 */
@Command("pet")
class PetCommand(private val plugin: TriumphPets) : CommandBase() {


    @Default
    fun help(player: Player) {

        EntityController().spawnPet(plugin, player.location, player)

    }

}