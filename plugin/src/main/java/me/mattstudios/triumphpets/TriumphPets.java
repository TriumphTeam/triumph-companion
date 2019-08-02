package me.mattstudios.triumphpets;

import me.mattstudios.triumphpets.pet.EntityController_1_14_R1;
import me.mattstudios.triumphpets.pet.EntityRegistry_1_14_R1;
import me.mattstudios.triumphpets.pet.PetController;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TriumphPets extends JavaPlugin implements CommandExecutor, Listener {

    private PetController petController;

    @Override
    public void onLoad() {
        EntityRegistry_1_14_R1.registerEntities();
    }

    @Override
    public void onEnable() {
        this.getCommand("pet").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);

        petController = new EntityController_1_14_R1();
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void on(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof PetController) event.setCancelled(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Location loc = player.getLocation();
        petController.spawnPet(loc, player);

        return false;
    }

}
