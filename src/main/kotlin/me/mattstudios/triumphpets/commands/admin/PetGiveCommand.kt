package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.components.FilterType
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet.utils.PetType
import org.apache.commons.lang.StringUtils
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
@Command("pet")
class PetGiveCommand(private val plugin: TriumphPets) : CommandBase() {

    private val dataManager = plugin.petManager.dataManager

    @SubCommand("give")
    fun give(sender: CommandSender, @Completion("#players") player: Player?, @Values("#pets") petType: PetType?) {
        if (player == null) {
            plugin.locale.sendMessage(sender, Message.COMMAND_GIVE_NO_PLAYER)
            return
        }

        val petPlayer = dataManager.getPetPlayer(player)

        if (petPlayer == null) {
            plugin.locale.sendMessage(sender, Message.COMMAND_GIVE_NO_PLAYER)
            return
        }

        if (petType == null) {
            plugin.locale.sendMessage(sender, Message.COMMAND_GIVE_NO_PET)
            return
        }

        // Adds the pet to the player
        val petMemory = PetMemory(plugin, dataManager.petConfig, FilterType.BLACK_LIST)
        dataManager.addPet(petPlayer, PetData(plugin, UUID.randomUUID(), petType, petType.defaultName, petMemory, player))
        sender.sendMessage(StringUtils.replace(plugin.locale.getMessage(Message.COMMAND_GIVE_SUCCESS), "{player}", player.name))
    }

}