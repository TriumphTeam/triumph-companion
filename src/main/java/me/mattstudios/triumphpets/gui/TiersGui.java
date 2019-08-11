package me.mattstudios.triumphpets.gui;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.mattstudios.triumphpets.TriumphPets;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.mattstudios.triumphpets.util.Utils.getCustomSkull;

public class TiersGui {

    private TriumphPets plugin;

    private final ItemStack TIER_1;
    private final ItemStack TIER_2;
    private final ItemStack TIER_3;

    TiersGui(TriumphPets plugin) {
        this.plugin = plugin;

        TIER_1 = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliMzAzMDNmOTRlN2M3ODVhMzFlNTcyN2E5MzgxNTM1ZGFmNDc1MzQ0OWVhNDFkYjc0NmUxMjM0ZTlkZDJiNSJ9fX0=");
        TIER_2 = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTNjYTdkN2MxNTM0ZGM2YjllZDE2NDdmOTAyNWRkZjI0NGUwMTA3ZGM4ZGQ0ZjRmMDg1MmM4MjA4MWQ2MzUwZSJ9fX0=");
        TIER_3 = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk1ZTFlMmZiMmRlN2U2Mjk5YTBmNjFkZGY3ZDlhNmQxMDFmOGQ2NjRmMTk1OWQzYjY3ZGNlOGIwNDlhOGFlMSJ9fX0=");
    }

    public void open(Player player) {
        Gui gui = new Gui(plugin, 3, "Pets menu");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane fillPane = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOW);
        OutlinePane contentPane = new OutlinePane(2, 1, 5, 1, Pane.Priority.HIGH);

        setUpFillPane(fillPane);
        gui.addPane(fillPane);

        setUpContentPane(contentPane, player);
        gui.addPane(contentPane);

        gui.show(player);
    }

    private void setUpContentPane(OutlinePane contentPane, Player player) {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        emptyItemStack(itemStack);

        contentPane.addItem(new GuiItem(TIER_1, event -> event.getWhoClicked().sendMessage("yah boy 1")));
        contentPane.addItem(new GuiItem(itemStack));
        contentPane.addItem(new GuiItem(TIER_2, event -> event.getWhoClicked().sendMessage("yah boy 2")));
        contentPane.addItem(new GuiItem(itemStack));
        contentPane.addItem(new GuiItem(TIER_3, event -> event.getWhoClicked().sendMessage("yah boy 3")));
    }

    private void setUpFillPane(OutlinePane fillPane) {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        emptyItemStack(itemStack);

        for (int i = 0; i < 27; i++) {
            fillPane.addItem(new GuiItem(itemStack));
        }
    }

    private void emptyItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
    }

}
