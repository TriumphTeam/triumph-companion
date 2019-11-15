package me.mattstudios.triumphpets.commands;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.command.CommandSender;

@Command("referral")
public class CMDGives extends CommandBase {

    private TriumphPets plugin;

    public CMDGives(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @Default
    @Permission("referrals.access")
    public void give(CommandSender player, String target) {
        System.out.println(target);

        /*player.sendMessage(player.getUniqueId().toString());
        player.sendMessage(playerName);
        player.sendMessage(petType.name());
        player.sendMessage(String.valueOf(tier));

        player.getInventory().getItemInMainHand();*/
    }

}
