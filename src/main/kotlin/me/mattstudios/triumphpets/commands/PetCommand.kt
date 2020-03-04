package me.mattstudios.triumphpets.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author Matt
 */
@Command("petadmin")
class PetCommand(private val plugin: TriumphPets) : CommandBase() {


    @SubCommand("give")
    @Completion("#players")
    fun give(player: Player, target: Player, petType: String) {

        plugin.petManager.databaseManager.addPet(PetData(UUID.randomUUID(), target.uniqueId, PetType.PET_SNOW_FOX_BABY, PetType.PET_SNOW_FOX_BABY.defaultName))
        //plugin.petManager.petController.spawnPet(player.location, player)
    }




}