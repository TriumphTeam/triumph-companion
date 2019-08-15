package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDPet extends BaseCommand {

    @Dependency
    private TriumphPets plugin;

    public CMDPet(TriumphPets plugin) {
        this.plugin = plugin;
    }

    /**
     * Default command for the pet, displays help.
     *
     * @param player The player doing the command.
     */
    @Default
    @Subcommand("help")
    @CommandPermission("triumphpets.help")
    public void help(Player player) {
        player.sendMessage("HELP");
    }

    @Subcommand("spawn")
    @CommandPermission("triumphpets.spawn")
    public void spawn(Player player) {
        plugin.getPetController().spawnPet(player.getLocation(), player);
    }
}
