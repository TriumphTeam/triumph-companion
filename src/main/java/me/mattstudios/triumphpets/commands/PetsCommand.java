package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.entity.Player;

@Command("pets")
public class PetsCommand extends CommandBase {

    private TriumphPets plugin;

    public PetsCommand(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @Default
    @Permission("triumphpets.list")
    public void list(Player player) {
        plugin.getGuiHandler().getGuiTiers().open(player);
    }

}
