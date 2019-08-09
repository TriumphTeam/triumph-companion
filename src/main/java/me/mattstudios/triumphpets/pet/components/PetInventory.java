package me.mattstudios.triumphpets.pet.components;

import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

import static me.mattstudios.triumphpets.util.Utils.decodeInventory;
import static me.mattstudios.triumphpets.util.Utils.encodeInventory;

public class PetInventory {

    private TriumphPets plugin;
    private Inventory inventory;
    private Player owner;

    public PetInventory(TriumphPets plugin, Player owner, int level) {
        this.plugin = plugin;
        this.owner = owner;

        inventory = Bukkit.getServer().createInventory(owner, level * 9);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void testSave() {

        System.out.println(Arrays.toString(inventory.getContents()));
        String test = encodeInventory(inventory);
        System.out.println(test);
        System.out.println(Arrays.toString(decodeInventory(owner, test).getContents()));

        /*ItemStack[] itemStacks = inventory.getContents();
        for (int i = 0; i < itemStacks.length; i++) {
            String encoded = encodeItem(itemStacks[i]);
            System.out.println("slot: "+i + " | " + encoded);
            System.out.println("slot: "+i + " | " + decodeItem(encoded));
        }*/
    }
}
