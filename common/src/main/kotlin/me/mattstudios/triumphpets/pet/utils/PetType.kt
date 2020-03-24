package me.mattstudios.triumphpets.pet.utils

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mfgui.gui.components.ItemBuilder
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
enum class PetType(val typeName: String, val defaultName: String, val item: ItemStack, val version: Int, val baby: Boolean) {

    PET_SNOW_FOX_BABY(
            "Baby Snow Fox",
            "Foxy",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem()).setSkullTexture("eyJ0aW1lc3RhbXAiOjE1NjU0Nzc0NjQ2MzgsInByb2ZpbGVJZCI6IjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwicHJvZmlsZU5hbWUiOiJHb2xkYXBmZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyNjlmMTBjNTBjZTdiZTBiZWYyYTI4N2MzNmM3NDA0MDEyZjI0OTg0MTU2NzU2M2M4NGRjNTcxYjEwNjU2ZDkifX19").build(),
            15,
            true
    ),
    PET_SNOW_FOX(
            "Snow Fox",
            "Pet Fox",
            ItemBuilder(XMaterial.PLAYER_HEAD.parseItem()).setSkullTexture("eyJ0aW1lc3RhbXAiOjE1NjU0NzgwODE4MDUsInByb2ZpbGVJZCI6IjJjMTA2NGZjZDkxNzQyODI4NGUzYmY3ZmFhN2UzZTFhIiwicHJvZmlsZU5hbWUiOiJOYWVtZSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTIwNzI1YzE1YTdiMDEwOWQ0MjVhYjA5MTg2OTYxNmFhYTY1MWVlNDMzODM4NjM5MDZiNDQxMGQxOGVhODZkZSJ9fX0=").build(),
            15,
            false
    )

}