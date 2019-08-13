package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDList extends BaseCommand {

    private TriumphPets plugin;

    public CMDList(TriumphPets plugin) {
        this.plugin = plugin;
    }

    /**
     * Default command for the pet, displays help.
     *
     * @param player The player doing the command.
     */
    @Default
    @Subcommand("list")
    @CommandAlias("pets")
    @CommandPermission("triumphpets.help")
    public void list(Player player) {
        plugin.getGuiHandler().getGuiTiers().open(player);
    }

}
