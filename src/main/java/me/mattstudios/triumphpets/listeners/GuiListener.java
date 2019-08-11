package me.mattstudios.triumphpets.listeners;

import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    private TriumphPets plugin;

    public GuiListener(TriumphPets plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        if (event.getInventory().getMaxStackSize() != 67) return;
        //event.setCancelled(true);

        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) return;
        if (!itemStack.hasItemMeta()) return;
        if (!itemStack.getItemMeta().hasLocalizedName()) return;

        switch (itemStack.getItemMeta().getLocalizedName()) {
            case "something1":
                event.getWhoClicked().sendMessage("Fuck yeah tier 1");
                break;

            case "something2":
                event.getWhoClicked().sendMessage("Fuck yeah tier 2");
                break;

            case "something3":
                event.getWhoClicked().sendMessage("Fuck yeah tier 3");
                break;
        }
    }
}
