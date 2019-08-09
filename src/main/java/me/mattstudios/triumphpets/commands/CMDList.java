package me.mattstudios.triumphpets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.data.petdata.PetData;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

import static me.mattstudios.triumphpets.util.Utils.getCustomSkull;

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
    public void onHelp(Player player) {
        for (PetData petData : plugin.getSqLiteManager().getPets(player)) {
            player.sendMessage("pet: " + petData.getPetName());
        }

        player.getInventory().addItem(getCustomSkull(PetType.PET_FOX_SNOW.getTexture()));
    }

}
