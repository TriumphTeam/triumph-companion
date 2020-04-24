package me.mattstudios.triumphpets.crate.components

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mfgui.gui.components.ItemBuilder
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
enum class CrateEgg(
        val eggName: String,
        private val item: ItemStack,
        val firstCrackItem: ItemStack,
        val secondCrackItem: ItemStack,
        val thirdCrackItem: ItemStack,
        val blockTexture: String
) {

    BLUE(
            "Blue",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4MDQ2MTAsInByb2ZpbGVJZCI6ImI3NDc5YmFlMjljNDRiMjNiYTU2MjgzMzc4ZjBlM2M2IiwicHJvZmlsZU5hbWUiOiJTeWxlZXgiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEyODRiMDNiNTY1ZjdhNmM2ZGMxN2ExOTA5NWZlNTg4OTI5MTk0M2E1M2UwNjIzYzc4ZTNjZTUzZjM0MGZhNGQifX19")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4NDAyNjQsInByb2ZpbGVJZCI6IjU2Njc1YjIyMzJmMDRlZTA4OTE3OWU5YzkyMDZjZmU4IiwicHJvZmlsZU5hbWUiOiJUaGVJbmRyYSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3Yzg3Y2FiNTJkMDgyNzRmMTI2ZjQ2ZGE5NTZkNDQ0YjhkZjA2YWE2YjA3OTc2MTRlZjMzMmQ4ZTFhZWVhMiJ9fX0=")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4NzQzODEsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80MDAzNTcwOTRlYmQyOWFiYTVhNzFjYTlkMGVhOWIzMmUxMGQ2N2UzZTIyOWNjYjdjZGJhOWY0Y2IxZGY4NjZkIn19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4OTQ1ODMsInByb2ZpbGVJZCI6ImU3OTNiMmNhN2EyZjQxMjZhMDk4MDkyZDdjOTk0MTdiIiwicHJvZmlsZU5hbWUiOiJUaGVfSG9zdGVyX01hbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDkzZmQyZmM1YzMwYTA4YjkzNThlYWQ2MjBjZTZhZWIwYmYwMTdhMzk4MTYyMjk5ZWUzMjU0YjkzMDgzODNiNyJ9fX0=")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODY0Nzg4MDQ2MTAsInByb2ZpbGVJZCI6ImI3NDc5YmFlMjljNDRiMjNiYTU2MjgzMzc4ZjBlM2M2IiwicHJvZmlsZU5hbWUiOiJTeWxlZXgiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEyODRiMDNiNTY1ZjdhNmM2ZGMxN2ExOTA5NWZlNTg4OTI5MTk0M2E1M2UwNjIzYzc4ZTNjZTUzZjM0MGZhNGQifX19"
    ),

    PURPLE(
            "Purple",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU4MzAzMjksInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNTUxODhlMGY4MjE0OTZmYjlhZjA0ZjVlNTVjYjk1NDhkOTJmYTNjYTc0M2FjNjNmNDAwZjg0ZTBmMDI4MDYzIn19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU3NjUyOTEsInByb2ZpbGVJZCI6IjkxOGEwMjk1NTlkZDRjZTZiMTZmN2E1ZDUzZWZiNDEyIiwicHJvZmlsZU5hbWUiOiJCZWV2ZWxvcGVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kM2FhMTMwYWFhZDA5ODZjOWVlZDA3ZjgwMzUzN2YxMTUxY2Y0MDc3NjRlMjQ0MzBlYjk3ZjA0YzJlMzAxNTQ2In19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU3NzM2NTIsInByb2ZpbGVJZCI6ImRkZWQ1NmUxZWY4YjQwZmU4YWQxNjI5MjBmN2FlY2RhIiwicHJvZmlsZU5hbWUiOiJEaXNjb3JkQXBwIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jMzYyNjk5OWU5MjNiYWEzZDdmZDhmMDhhMjI3ZWFjNzdlZGM0ZmU5ZWM4YzJmZjFkODJlNGU0OGQwZjk0NWVlIn19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU4MjA0MDksInByb2ZpbGVJZCI6IjJkYzc3YWU3OTQ2MzQ4MDI5NDI4MGM4NDIyNzRiNTY3IiwicHJvZmlsZU5hbWUiOiJzYWR5MDYxMCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTJhYmExODk5NGNhOTIzZjExYTM0NjFhZjMwNDZkMWY0MWI1ZTg3OWY3MzU1MjAwMDFiZTVmNjU5YTMxNzQ4MCJ9fX0=")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODczMTU4MzAzMjksInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNTUxODhlMGY4MjE0OTZmYjlhZjA0ZjVlNTVjYjk1NDhkOTJmYTNjYTc0M2FjNjNmNDAwZjg0ZTBmMDI4MDYzIn19fQ=="
    ),

    RED(
            "Red",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk2NzkxNzksInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxN2ZjMTFkY2UzMWUwMDZlYWYxZjVhZDMwMjBiMjVkMTllMmI4ZTM2YmYwYjViNzBkZTQzY2M3YzVmNTNlOGEifX19")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3NTg3NTAsInByb2ZpbGVJZCI6ImRkZWQ1NmUxZWY4YjQwZmU4YWQxNjI5MjBmN2FlY2RhIiwicHJvZmlsZU5hbWUiOiJEaXNjb3JkQXBwIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ZjZjNDJmOTY3NDkyOTk1NjQzZmE2YzVjNWY0YmRkYTA3MTBjMzA4NWQwYWRhNGEwMzg4MDJlNTU2ZjdiNjNiIn19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3ODM2NjAsInByb2ZpbGVJZCI6ImQ2MGYzNDczNmExMjQ3YTI5YjgyY2M3MTViMDA0OGRiIiwicHJvZmlsZU5hbWUiOiJCSl9EYW5pZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFmODcyZDNmYTBiMGIyYzNiZTA4ZmE4NjE2MjA3ZDJjNmEwYTIzMTBmMDkxMzM0MDE0OWNhOWEyN2E0ZjMyYWIifX19")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODY0Nzk3OTk3ODYsInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIxYzYzZDIyMTFhYzBmY2U1NTYzZjZkZGI3N2RjYjk4YmMzYjFmYmUzOTdiYTM2OGM4ODRkMTZhYTAxYmE0YyJ9fX0=")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODY0Nzk2NzkxNzksInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxN2ZjMTFkY2UzMWUwMDZlYWYxZjVhZDMwMjBiMjVkMTllMmI4ZTM2YmYwYjViNzBkZTQzY2M3YzVmNTNlOGEifX19"
    ),

    YELLOW(
            "Yellow",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU5MjIyNTEsInByb2ZpbGVJZCI6IjRkNzA0ODZmNTA5MjRkMzM4NmJiZmM5YzEyYmFiNGFlIiwicHJvZmlsZU5hbWUiOiJzaXJGYWJpb3pzY2hlIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zYmE4MDlkZTg0YmUzYTFhYzEzZWE2YjRjMThlMmQxOTZhZjFlMWUzN2RhYThkYTA2ZDgyYWU1ZDQwNGY5MzMwIn19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU5MzUyMjksInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU3OGRmNTcxYmZjMzM1Mjc5Mjk3ZDBlYjgzMWM1ZmViMzdhZTUwMTViODUyYzc0OWRjN2ZmZDMyZmViNzRlNGQifX19")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU5NDQ1OTAsInByb2ZpbGVJZCI6IjkxZjA0ZmU5MGYzNjQzYjU4ZjIwZTMzNzVmODZkMzllIiwicHJvZmlsZU5hbWUiOiJTdG9ybVN0b3JteSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3Mzk5NmQyMmUyZDZjZGVhMGE2OTY3ZmJiN2NiMWQwNjIyNDAzNjk1NTJlMzBhOThlMGE5MzY4MTNhZTM0YiJ9fX0=")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTYwMjkwMjksInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82NTNmMDU1ZWJhYzRhZGM0MGVlOWJkNGMxNDFkN2VmYTc3NGU0OTZiMWQ3NDZhOGZjMGUxZTY4YzMwNGNiNWUifX19")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODczMTU5MjIyNTEsInByb2ZpbGVJZCI6IjRkNzA0ODZmNTA5MjRkMzM4NmJiZmM5YzEyYmFiNGFlIiwicHJvZmlsZU5hbWUiOiJzaXJGYWJpb3pzY2hlIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zYmE4MDlkZTg0YmUzYTFhYzEzZWE2YjRjMThlMmQxOTZhZjFlMWUzN2RhYThkYTA2ZDgyYWU1ZDQwNGY5MzMwIn19fQ=="
    ),

    GREEN(
            "Green",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU0NDM0MTMsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZGZkOWY3NTA5OThhNzBhMmYyNDA0OGYyYjhmMDM4OWQ3OTQyMjQyMGMwZDI1ZWVhN2U2YmJhNGZhYTZjY2Q5In19fQ==")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU0NTM5NDgsInByb2ZpbGVJZCI6IjkxZjA0ZmU5MGYzNjQzYjU4ZjIwZTMzNzVmODZkMzllIiwicHJvZmlsZU5hbWUiOiJTdG9ybVN0b3JteSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJlMjMyOGRiOGUwMTFlYjZkZjVmZmY0YmU0MDc4NmJjOTU1YjgwNzJlMzU2OTE5NDkxYWZiOTgwYmU4YTJkNSJ9fX0=")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU0NjU0MzIsInByb2ZpbGVJZCI6IjJkYzc3YWU3OTQ2MzQ4MDI5NDI4MGM4NDIyNzRiNTY3IiwicHJvZmlsZU5hbWUiOiJzYWR5MDYxMCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YzZGM2ZjI1YmRmZTQ0MjBhOTQ3YjU1ZDY1ZGUyMzNhZGMxMTQwOWE3NmIzZDMxNDM1NzJkZDNmYzZhZDdlNiJ9fX0=")
                    .build(),
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .setSkullTexture("eyJ0aW1lc3RhbXAiOjE1ODczMTU0NzYwODMsInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVlOWI4YTAxODZiNTFlMGQ2ZTFkOGZkYzMxYzk5YjBhNTM0NjQ4ODUwMWNkM2ZhNDVmMTA5NzYxNmViM2JiZCJ9fX0=")
                    .build(),
            "eyJ0aW1lc3RhbXAiOjE1ODczMTU0NDM0MTMsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZGZkOWY3NTA5OThhNzBhMmYyNDA0OGYyYjhmMDM4OWQ3OTQyMjQyMGMwZDI1ZWVhN2U2YmJhNGZhYTZjY2Q5In19fQ=="
    );

    /**
     * Gets a clone of the item
     */
    fun getItem(): ItemStack {
        return item.clone();
    }

}