package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Values;
import com.google.common.collect.ImmutableList;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDGive extends BaseCommand {

    @Dependency
    private TriumphPets plugin;

    public CMDGive(TriumphPets plugin) {
        this.plugin = plugin;

        plugin.getCommandManager().getCommandCompletions().registerCompletion("give", c -> {
            return ImmutableList.of("some", "custom", "completion");
        });
        plugin.getCommandManager().getCommandCompletions().registerCompletion("pet_type", c -> {
            return ImmutableList.of("some", "custom", "completion");
        });
        plugin.getCommandManager().getCommandCompletions().registerCompletion("numbers", c -> {
            return ImmutableList.of("some", "custom", "completion");
        });
    }

    @Subcommand("give")
    @CommandPermission("triumphpets.spawn")
    @CommandCompletion("@players @range:0-20")
    public void give(Player player, @Values("@players") Player playerName, PetType petType, @Values("@range:0-20") int tier) {

        player.sendMessage(player.getName());
        player.sendMessage(playerName.getName());
        player.sendMessage(petType.name());
        player.sendMessage(String.valueOf(tier));

        player.getInventory().getItemInMainHand();
    }

}
