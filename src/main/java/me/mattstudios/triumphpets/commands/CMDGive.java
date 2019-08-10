package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDGive extends BaseCommand {

    @Dependency
    private TriumphPets plugin;

    public CMDGive(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @Subcommand("give")
    @CommandPermission("triumphpets.spawn")
    public void spawn(Player player, String playerName, PetType petType, int tier) {

        player.sendMessage(player.getName());
        player.sendMessage(playerName);
        player.sendMessage(petType.name());
        player.sendMessage(String.valueOf(tier));

        player.sendMessage(PetType.PET_WOLF.name());
    }

}
