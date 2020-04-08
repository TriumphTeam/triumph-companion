package me.mattstudios.triumphpets.util

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mfgui.gui.components.ItemBuilder
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
enum class Items(val item: ItemStack, val texture: String) {

    // Empty item for the pets menu
    EMPTY_PET_ITEM(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4ZWExZjUxZjI1M2ZmNTE0MmNhMTFhZTQ1MTkzYTRhZDhjM2FiNWU5YzZlZWM4YmE3YTRmY2I3YmFjNDAifX19")
                    .build(),
            ""
    ),

    // Cate item for the pets crate
    CRATE_ITEM(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODU5NjUwOTQwMzksInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xOTgyMmE3YWY4YWU1MGVlOGM1NzQzOGM4ZjgwODFjNGI1ZmUwY2Y5OGQzNDdiNjc4ZjExZWIxODhiMmUwMDdjIn19fQ==")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODU5NjUwOTQwMzksInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xOTgyMmE3YWY4YWU1MGVlOGM1NzQzOGM4ZjgwODFjNGI1ZmUwY2Y5OGQzNDdiNjc4ZjExZWIxODhiMmUwMDdjIn19fQ=="
    ),

    // Cate item for animation first crack
    CRATE_ITEM_CRACK_1(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODYyOTkzNjY4NzksInByb2ZpbGVJZCI6ImQ2MGYzNDczNmExMjQ3YTI5YjgyY2M3MTViMDA0OGRiIiwicHJvZmlsZU5hbWUiOiJCSl9EYW5pZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2FkMTAxMWJlZWIzMGMxMDAzZDMwNmYyMDc0MjhiOGFjMTYzNTQ3OTRiZjUwMDEzYjZjNTVjOThmNjhlN2FkZmMifX19")
                    .build(),
            ""
    ),

    // Crate item for animation second crack
    CRATE_ITEM_CRACK_2(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODYyOTk0MTg0MDMsInByb2ZpbGVJZCI6ImJlY2RkYjI4YTJjODQ5YjRhOWIwOTIyYTU4MDUxNDIwIiwicHJvZmlsZU5hbWUiOiJTdFR2Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MWU4YmE4MmI0N2M4YmUyY2Q4MDc5ZWZlNjE2MGJhMjljZjc0ODNlZGJjYjA3ZGIxMjJmMDU0NDNjNjk5ZTk1In19fQ==")
                    .build(),
            ""
    ),

    CRATE_ITEM_CRACK_3(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODYzMDUxMTMyNDQsInByb2ZpbGVJZCI6IjkxOGEwMjk1NTlkZDRjZTZiMTZmN2E1ZDUzZWZiNDEyIiwicHJvZmlsZU5hbWUiOiJCZWV2ZWxvcGVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hMTk2MGUyNzAzYzdiNzRjMzVjMWJkZjI3MzNjZGEwN2MwYzNkYzJlYTljODY1YmUxZGM3OTVlMzQwYzhhYTBiIn19fQ==")
                    .build(),
            ""
    )

}