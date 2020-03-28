package me.mattstudios.triumphpets.util

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mfgui.gui.components.ItemBuilder
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
enum class Items(val item: ItemStack) {

    // Empty item for the pets menu
    EMPTY_PET_ITEM(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4ZWExZjUxZjI1M2ZmNTE0MmNhMTFhZTQ1MTkzYTRhZDhjM2FiNWU5YzZlZWM4YmE3YTRmY2I3YmFjNDAifX19")
                    .build()
    ),
    // Cate item for the pets crate
    CRATE_ITEM(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDY0MmFmYTM5Njg1M2I4MWIxN2JlZjVjOGQ3YTQ0YzEyZGU2ODlhNTZhZjQ3NDg0NjY3OTgzOTlkYTNjZmVhZSJ9fX0=")
                    .build()
    )

}