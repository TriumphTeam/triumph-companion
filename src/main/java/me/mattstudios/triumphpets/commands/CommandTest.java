package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.entity.Player;

@Command("testtp")
public class CommandTest extends CommandBase {

    @Default
    public void test(Player player, Player test) {
        player.teleport(test);
    }

}
