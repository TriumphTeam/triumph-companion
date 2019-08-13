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

import java.util.ArrayList;
import java.util.List;

import static me.mattstudios.triumphpets.util.Utils.getCustomSkull;
import static me.mattstudios.utils.MessageUtils.color;

public class GuiTiers {

    private TriumphPets plugin;

    private final ItemStack TIER_1;
    private final ItemStack TIER_2;
    private final ItemStack TIER_3;

    GuiTiers(TriumphPets plugin) {
        this.plugin = plugin;

        TIER_1 = getCustomSkull("eyJ0aW1lc3RhbXAiOjE1NjU1NzczNTQzNjksInByb2ZpbGVJZCI6ImJlY2RkYjI4YTJjODQ5YjRhOWIwOTIyYTU4MDUxNDIwIiwicHJvZmlsZU5hbWUiOiJTdFR2Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xOTgyMmE3YWY4YWU1MGVlOGM1NzQzOGM4ZjgwODFjNGI1ZmUwY2Y5OGQzNDdiNjc4ZjExZWIxODhiMmUwMDdjIn19fQ==");
        TIER_2 = getCustomSkull("eyJ0aW1lc3RhbXAiOjE1NjU1NzcyODgyMjgsInByb2ZpbGVJZCI6IjU3MGIwNWJhMjZmMzRhOGViZmRiODBlY2JjZDdlNjIwIiwicHJvZmlsZU5hbWUiOiJMb3JkU29ubnkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI1M2JkM2JjMGY0YWZkMmZjOTFiOTE5MGI2NjIzOTQ5M2M2ZGExYjViNDQ4NTYyZTc2MTc4ZTgxNDY1ZGM1ODAifX19");
        TIER_3 = getCustomSkull("eyJ0aW1lc3RhbXAiOjE1NjU1NzY4MjI3OTAsInByb2ZpbGVJZCI6IjVkMjRiYTBiMjg4YzQyOTM4YmExMGVjOTkwNjRkMjU5IiwicHJvZmlsZU5hbWUiOiIxbnYzbnQxdjN0NGwzbnQiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q4MzUxODg5MzYwMjliNzNkNmE5YTE0YjA2ZjM3ODA5Zjk4MzU4NGNhZTc3OWViZjIxMjI5ODlhZmZhMmZjZjgifX19");
    }

    public void open(Player player) {
        Gui gui = new Gui(plugin, 3, "Select tier");
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

        setUpTierData(player);

        contentPane.addItem(new GuiItem(TIER_1, event -> event.getWhoClicked().sendMessage("Tier 1 pets")));
        contentPane.addItem(new GuiItem(itemStack));
        contentPane.addItem(new GuiItem(TIER_2, event -> event.getWhoClicked().sendMessage("yah boy 2")));
        contentPane.addItem(new GuiItem(itemStack));
        contentPane.addItem(new GuiItem(TIER_3, event -> event.getWhoClicked().sendMessage("yah boy 3")));
    }

    private void setUpTierData(Player player) {
        ItemMeta itemMeta = TIER_1.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(color("&f&lTier 1 Pets"));

        lore.add(color(" "));
        lore.add(color("&7See your current tier 1 Pets!"));
        lore.add(color(" "));
        lore.add(color("&7Pets: &f" + plugin.getPetDataHandler().getPets(player.getUniqueId().toString(), (short) 1).size()));
        lore.add(color(" "));
        itemMeta.setLore(lore);

        TIER_1.setItemMeta(itemMeta);

        lore.clear();
        itemMeta = TIER_2.getItemMeta();
        itemMeta.setDisplayName(color("&b&lTier 2 pets"));

        lore.add(color(" "));
        lore.add(color("&7See your current tier 2 Pets!"));
        lore.add(color(" "));
        lore.add(color("&7Pets: &b" + plugin.getPetDataHandler().getPets(player.getUniqueId().toString(), (short) 2).size()));
        lore.add(color(" "));
        itemMeta.setLore(lore);

        TIER_2.setItemMeta(itemMeta);

        lore.clear();
        itemMeta = TIER_3.getItemMeta();
        itemMeta.setDisplayName(color("&3&lTier 3 Pets"));

        lore.add(color(" "));
        lore.add(color("&7See your current tier 3 Pets!"));
        lore.add(color(" "));
        lore.add(color("&7Pets: &3" + plugin.getPetDataHandler().getPets(player.getUniqueId().toString(), (short) 3).size()));
        lore.add(color(" "));
        itemMeta.setLore(lore);

        TIER_3.setItemMeta(itemMeta);

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
