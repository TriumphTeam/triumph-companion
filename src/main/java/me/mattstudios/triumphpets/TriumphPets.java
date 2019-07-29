package me.mattstudios.triumphpets;

import me.mattstudios.triumphpets.pet.PetRegistry;
import me.mattstudios.triumphpets.pet.pets.PetFox;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TriumphPets extends JavaPlugin implements CommandExecutor, Listener {

    //private HashMap<Player, PetEntity> pets = new HashMap<>();

    @Override
    public void onLoad() {
        PetRegistry.registerEntities();
    }

    @Override
    public void onEnable() {
        this.getCommand("pet").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void on(PlayerInteractEntityEvent event) {
        event.getPlayer().sendMessage(event.getRightClicked().getUniqueId().toString());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Location loc = player.getLocation();
        PetFox petFox = new PetFox(((CraftWorld) player.getWorld()).getHandle(), player);
        petFox.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        //pets.put(player, petFox);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(petFox);

        return false;
    }

}
