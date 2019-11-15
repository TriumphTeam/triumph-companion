package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.Player;

@Command("pet")
public class CMDPet extends CommandBase {

    private TriumphPets plugin;

    public CMDPet(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @Default
    @Permission("triumphpets.help")
    public void help(Player player) {
        player.sendMessage("HELP");
    }

    @SubCommand("spawn")
    @Permission("triumphpets.spawn")
    public void spawn(Player player) {
        plugin.getPetController().spawnPet(player.getLocation(), player);
    }
}
