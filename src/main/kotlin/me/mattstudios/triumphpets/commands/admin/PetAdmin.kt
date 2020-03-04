package me.mattstudios.triumphpets.commands.admin

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.annotations.Values
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
@Command("petadmin")
class PetAdmin(private val plugin: TriumphPets) : CommandBase() {

    @SubCommand("give")
    fun give(sender: CommandSender, @Completion("#players") player: Player?, @Values("#pets") petType: PetType?) {
        if (player == null) {
            sender.sendMessage("invalid player")
            return
        }

        if (petType == null) {
            sender.sendMessage("invalid pet type")
            return
        }

        plugin.petManager.databaseManager.addPet(PetData(UUID.randomUUID(), player.uniqueId, petType, petType.defaultName))
    }

}