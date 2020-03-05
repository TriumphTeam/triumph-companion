package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
@Command("pet")
class PetGive(private val plugin: TriumphPets) : CommandBase() {

    @SubCommand("give")
    fun give(sender: CommandSender, @Completion("#players") player: Player?, @Values("#pets") petType: PetType?) {
        if (player == null) {
            plugin.locale.sendMessage(sender, Message.COMMAND_GIVE_NO_PLAYER)
            return
        }

        if (petType == null) {
            plugin.locale.sendMessage(sender, Message.COMMAND_GIVE_NO_PET)
            return
        }

        plugin.petManager.dataManager.addPet(sender, player, PetData(UUID.randomUUID(), player.uniqueId, petType, petType.defaultName))
    }

}