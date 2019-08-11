package me.mattstudios.triumphpets.gui;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static me.mattstudios.triumphpets.util.Utils.getCustomSkull;
import static me.mattstudios.utils.MessageUtils.color;

public class GuiPets {

    private TriumphPets plugin;

    private final String TIER_1_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliMzAzMDNmOTRlN2M3ODVhMzFlNTcyN2E5MzgxNTM1ZGFmNDc1MzQ0OWVhNDFkYjc0NmUxMjM0ZTlkZDJiNSJ9fX0=";
    private final String TIER_2_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTNjYTdkN2MxNTM0ZGM2YjllZDE2NDdmOTAyNWRkZjI0NGUwMTA3ZGM4ZGQ0ZjRmMDg1MmM4MjA4MWQ2MzUwZSJ9fX0=";
    private final String TIER_3_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk1ZTFlMmZiMmRlN2U2Mjk5YTBmNjFkZGY3ZDlhNmQxMDFmOGQ2NjRmMTk1OWQzYjY3ZGNlOGIwNDlhOGFlMSJ9fX0=";

    private Inventory tierGui;

    public GuiPets(TriumphPets plugin) {
        this.plugin = plugin;

        setupInventory();
    }

    public void openGuiTier(Player player) {
        Gui gui = new Gui(plugin, 3, "Pages!");

        OutlinePane backgroundPane = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOW);
        createBackgroundItems(backgroundPane);

        gui.addPane(backgroundPane);
        gui.show(player);
    }

    /**
     * Create the background panes
     * @param pane the pane to add to
     */
    private void createBackgroundItems(OutlinePane pane) {
        // Loop through 27 (three rows)
        for (int i = 0; i < 27; i++) {
            // Add the pane item to the GUI and cancel the click event on it
            pane.addItem(new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), event -> event.setCancelled(true)));
        }
    }

    private void setupInventory() {
        new Thread(() -> {
            tierGui = Bukkit.createInventory(null, 27);
            tierGui.setMaxStackSize(67);

            ItemStack itemStack;
            ItemMeta itemMeta;
            for (int i = 0; i < tierGui.getSize(); i++) {

                switch (i) {
                    case 11:
                        itemStack = getCustomSkull(TIER_1_TEXTURE);
                        itemMeta = itemStack.getItemMeta();

                        itemMeta.setDisplayName(color("&cTier 1 pets"));
                        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemMeta.setLore(Collections.singletonList("Change later ofc"));
                        itemMeta.setLocalizedName("something1");

                        itemStack.setItemMeta(itemMeta);
                        tierGui.setItem(i, itemStack);
                        break;
                    case 13:
                        itemStack = getCustomSkull(TIER_2_TEXTURE);
                        itemMeta = itemStack.getItemMeta();

                        itemMeta.setDisplayName(color("&cTier 2 pets"));
                        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemMeta.setLore(Collections.singletonList("Change later ofc"));
                        itemMeta.setLocalizedName("something2");

                        itemStack.setItemMeta(itemMeta);
                        tierGui.setItem(i, itemStack);
                        break;
                    case 15:
                        itemStack = getCustomSkull(TIER_3_TEXTURE);
                        itemMeta = itemStack.getItemMeta();

                        itemMeta.setDisplayName(color("&cTier 3 pets"));
                        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemMeta.setLore(Collections.singletonList("Change later ofc"));
                        itemMeta.setLocalizedName("something3");

                        itemStack.setItemMeta(itemMeta);
                        tierGui.setItem(i, itemStack);
                        break;
                    default:
                        itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        itemMeta = itemStack.getItemMeta();

                        itemMeta.setDisplayName(" ");
                        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                        itemStack.setItemMeta(itemMeta);
                        tierGui.setItem(i, itemStack);
                        break;
                }

            }
        }).start();
    }

}
