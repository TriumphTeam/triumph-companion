package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDPets extends BaseCommand {

    /**
     * Default command for the pet, displays help.
     *
     * @param player The player doing the command.
     */
    @Default
    @Subcommand("list")
    @CommandAlias("pets")
    @CommandPermission("triumphpets.help")
    public void onHelp(Player player) {
        player.sendMessage("list or pets");
    }

}
