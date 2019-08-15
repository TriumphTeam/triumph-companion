package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

@CommandAlias("pet")
public class CMDGive extends BaseCommand {

    @Dependency
    private TriumphPets plugin;

    public CMDGive(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @Subcommand("give")
    @CommandPermission("triumphpets.spawn")
    @CommandCompletion("@players @pet_types @range:1-3")
    public void give(Player player, String playerName, PetType petType, int tier) {
        player.sendMessage(player.getUniqueId().toString());
        player.sendMessage(playerName);
        player.sendMessage(petType.name());
        player.sendMessage(String.valueOf(tier));

        player.getInventory().getItemInMainHand();
    }

}
