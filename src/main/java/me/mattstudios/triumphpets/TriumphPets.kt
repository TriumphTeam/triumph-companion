package me.mattstudios.triumphpets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.commands.PetCommand

/**
 * @author Matt
 */
class TriumphPets : MattPlugin() {

    override fun onPluginEnable() {

        registerCommands(PetCommand(this))

    }

}