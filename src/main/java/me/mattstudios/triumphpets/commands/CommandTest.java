package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;

@Command("commandtest")
public class CommandTest extends CommandBase {

    @Default
    public void defaultCommand(CommandSender commandSender) {
        commandSender.sendMessage("Hello!");
    }

    @SubCommand("test")
    public void testSubCommand(CommandSender commandSender) {
        commandSender.sendMessage("Test!");
    }

}
