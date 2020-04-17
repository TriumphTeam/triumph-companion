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

    /**
     * Crate Items
     */

    // Blue
    CRATE_ITEM_BLUE(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4MDQ2MTAsInByb2ZpbGVJZCI6ImI3NDc5YmFlMjljNDRiMjNiYTU2MjgzMzc4ZjBlM2M2IiwicHJvZmlsZU5hbWUiOiJTeWxlZXgiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEyODRiMDNiNTY1ZjdhNmM2ZGMxN2ExOTA5NWZlNTg4OTI5MTk0M2E1M2UwNjIzYzc4ZTNjZTUzZjM0MGZhNGQifX19")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4MDQ2MTAsInByb2ZpbGVJZCI6ImI3NDc5YmFlMjljNDRiMjNiYTU2MjgzMzc4ZjBlM2M2IiwicHJvZmlsZU5hbWUiOiJTeWxlZXgiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEyODRiMDNiNTY1ZjdhNmM2ZGMxN2ExOTA5NWZlNTg4OTI5MTk0M2E1M2UwNjIzYzc4ZTNjZTUzZjM0MGZhNGQifX19"
    ),
    CRATE_ITEM_BLUE_CRACK_FIRST(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4NDAyNjQsInByb2ZpbGVJZCI6IjU2Njc1YjIyMzJmMDRlZTA4OTE3OWU5YzkyMDZjZmU4IiwicHJvZmlsZU5hbWUiOiJUaGVJbmRyYSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3Yzg3Y2FiNTJkMDgyNzRmMTI2ZjQ2ZGE5NTZkNDQ0YjhkZjA2YWE2YjA3OTc2MTRlZjMzMmQ4ZTFhZWVhMiJ9fX0=")
                    .build(),
            ""
    ),
    CRATE_ITEM_BLUE_CRACK_SECOND(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4NzQzODEsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80MDAzNTcwOTRlYmQyOWFiYTVhNzFjYTlkMGVhOWIzMmUxMGQ2N2UzZTIyOWNjYjdjZGJhOWY0Y2IxZGY4NjZkIn19fQ==")
                    .build(),
            ""
    ),
    CRATE_ITEM_BLUE_CRACK_THIRD(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4OTQ1ODMsInByb2ZpbGVJZCI6ImU3OTNiMmNhN2EyZjQxMjZhMDk4MDkyZDdjOTk0MTdiIiwicHJvZmlsZU5hbWUiOiJUaGVfSG9zdGVyX01hbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDkzZmQyZmM1YzMwYTA4YjkzNThlYWQ2MjBjZTZhZWIwYmYwMTdhMzk4MTYyMjk5ZWUzMjU0YjkzMDgzODNiNyJ9fX0=")
                    .build(),
            ""
    ),

    // Blue
    CRATE_ITEM_RED(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk2NzkxNzksInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxN2ZjMTFkY2UzMWUwMDZlYWYxZjVhZDMwMjBiMjVkMTllMmI4ZTM2YmYwYjViNzBkZTQzY2M3YzVmNTNlOGEifX19")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODY0Nzk2NzkxNzksInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxN2ZjMTFkY2UzMWUwMDZlYWYxZjVhZDMwMjBiMjVkMTllMmI4ZTM2YmYwYjViNzBkZTQzY2M3YzVmNTNlOGEifX19"
    ),
    CRATE_ITEM_RED_CRACK_FIRST(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3NTg3NTAsInByb2ZpbGVJZCI6ImRkZWQ1NmUxZWY4YjQwZmU4YWQxNjI5MjBmN2FlY2RhIiwicHJvZmlsZU5hbWUiOiJEaXNjb3JkQXBwIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ZjZjNDJmOTY3NDkyOTk1NjQzZmE2YzVjNWY0YmRkYTA3MTBjMzA4NWQwYWRhNGEwMzg4MDJlNTU2ZjdiNjNiIn19fQ==")
                    .build(),
            ""
    ),
    CRATE_ITEM_RED_CRACK_SECOND(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3ODM2NjAsInByb2ZpbGVJZCI6ImQ2MGYzNDczNmExMjQ3YTI5YjgyY2M3MTViMDA0OGRiIiwicHJvZmlsZU5hbWUiOiJCSl9EYW5pZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFmODcyZDNmYTBiMGIyYzNiZTA4ZmE4NjE2MjA3ZDJjNmEwYTIzMTBmMDkxMzM0MDE0OWNhOWEyN2E0ZjMyYWIifX19")
                    .build(),
            ""
    ),
    CRATE_ITEM_RED_CRACK_THIRD(
            ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3OTk3ODYsInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIxYzYzZDIyMTFhYzBmY2U1NTYzZjZkZGI3N2RjYjk4YmMzYjFmYmUzOTdiYTM2OGM4ODRkMTZhYTAxYmE0YyJ9fX0=")
                    .build(),
            ""
    )

}