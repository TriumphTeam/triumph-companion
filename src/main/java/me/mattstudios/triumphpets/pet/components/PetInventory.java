package me.mattstudios.triumphpets.pet.components;

import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.mattstudios.triumphpets.util.Utils.decodeItem;
import static me.mattstudios.triumphpets.util.Utils.encodeItem;

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
        ItemStack[] itemStacks = inventory.getContents();
        for (int i = 0; i < itemStacks.length; i++) {
            String encoded = encodeItem(itemStacks[i]);
            System.out.println("slot: "+i + " | " + encoded);
            System.out.println("slot: "+i + " | " + decodeItem(encoded));
        }
    }
}
