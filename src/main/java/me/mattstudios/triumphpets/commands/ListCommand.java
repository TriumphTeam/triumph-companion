package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.Player;

@Command("pet")
public class ListCommand extends CommandBase {

    private TriumphPets plugin;

    public ListCommand(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @SubCommand("list")
    @Permission("triumphpets.list")
    public void list(Player player) {
        plugin.getGuiHandler().getGuiTiers().open(player);
    }

}
