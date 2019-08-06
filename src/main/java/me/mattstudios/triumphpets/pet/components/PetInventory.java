package me.mattstudios.triumphpets.pet.components;

import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PetInventory {

    private TriumphPets plugin;
    private Inventory inventory;

    public PetInventory(TriumphPets plugin, Player owner, int level) {
        this.plugin = plugin;
        inventory = Bukkit.getServer().createInventory(owner, level * 9);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void testSave() {
        plugin.getConfig().set("test.inv", inventory);
        plugin.saveConfig();
    }
}
