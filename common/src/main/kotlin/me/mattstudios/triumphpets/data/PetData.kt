package me.mattstudios.triumphpets.data

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.locale.Locale
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet.utils.PetType
import org.apache.commons.lang.StringUtils.replace
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import java.util.UUID

/**
 * @author Matt
 */
data class PetData(
        private val plugin: MattPlugin, val uuid: UUID,
        val type: PetType, var name: String, val petMemory: PetMemory,
        private val owner: OfflinePlayer) {

    val petInventory = PetInventory(plugin, this, owner)

    /**
     * Gets the pet item with all the values in it
     */
    fun getPetItem(locale: Locale, actionMessage: String): ItemStack {
        return ItemBuilder(type.item.clone())
                .setName(color("&r" + replace(locale.getMessageRaw(Message.PET_DATA_DISPLAY_TITLE), "{name}", name)))
                .setLore(replaceList(locale.getMessageRaw(Message.PET_DATA_DISPLAY_LORE), actionMessage))
                .build()
    }

    /**
     * Replaces all the values in the list and colors it
     */
    private fun replaceList(lore: MutableList<String>, actionMessage: String): MutableList<String> {
        val replaced = mutableListOf<String>()

        for (indexLine in lore) {
            var line = indexLine

            line = replace(line, "{level}", petMemory.petExperience.level.toString())
            line = replace(line, "{max_level}", "5")
            line = replace(line, "{age}", "10")
            line = replace(line, "{type}", type.typeName)
            line = replace(line, "{action}", actionMessage)
            line = replace(line, "{xp_bar}", "&a--------&c--")
            line = replace(line, "{xp}", petMemory.petExperience.xp.toString())
            line = replace(line, "{level_xp}", "10")

            replaced.add(line)
        }

        return color(replaced)
    }
}