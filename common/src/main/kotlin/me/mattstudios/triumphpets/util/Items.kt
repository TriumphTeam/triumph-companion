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
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODYyMjMxMzg5OTAsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NzQyYjIzNDBjOTMyZWQ1ZGY5YTg5NTM0ODE4MDEyOWUwZWJjNzAyMTc2M2NmN2Y0NmIyYmJiZmNhYjY2ZDBjIn19fQ==")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODYyMjMxMzg5OTAsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NzQyYjIzNDBjOTMyZWQ1ZGY5YTg5NTM0ODE4MDEyOWUwZWJjNzAyMTc2M2NmN2Y0NmIyYmJiZmNhYjY2ZDBjIn19fQ=="
    ),

    // Crate item for animation second crack
    CRATE_ITEM_CRACK_2(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODYyMjQyMjQ0MzYsInByb2ZpbGVJZCI6ImRlNTcxYTEwMmNiODQ4ODA4ZmU3YzlmNDQ5NmVjZGFkIiwicHJvZmlsZU5hbWUiOiJNSEZfTWluZXNraW4iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzM3MGMzNTk4NzA3ZTZkOWFiZmZlOWY1Mjc5NDU5MTZhM2E4ODkwNmMwZWI1ZGY3NGFlNDk5ZGE2OWYzMDkxZWIifX19")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODYyMjQyMjQ0MzYsInByb2ZpbGVJZCI6ImRlNTcxYTEwMmNiODQ4ODA4ZmU3YzlmNDQ5NmVjZGFkIiwicHJvZmlsZU5hbWUiOiJNSEZfTWluZXNraW4iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzM3MGMzNTk4NzA3ZTZkOWFiZmZlOWY1Mjc5NDU5MTZhM2E4ODkwNmMwZWI1ZGY3NGFlNDk5ZGE2OWYzMDkxZWIifX19"
    )

}