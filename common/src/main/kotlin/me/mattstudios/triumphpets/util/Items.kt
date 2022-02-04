package me.mattstudios.triumphpets.util

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.components.ItemBuilder
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
enum class Items(private val item: ItemStack, val texture: String) {

    // Empty item for the pets menu
    EMPTY_PET_ITEM(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4ZWExZjUxZjI1M2ZmNTE0MmNhMTFhZTQ1MTkzYTRhZDhjM2FiNWU5YzZlZWM4YmE3YTRmY2I3YmFjNDAifX19")
                    .build(),
            ""
    ),

    FILL_ITEM(
            ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).setName(color("&r")).build(),
            ""
    );

    fun getItem(): ItemStack {
        return item.clone()
    }

}