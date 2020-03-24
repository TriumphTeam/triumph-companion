package me.mattstudios.triumphpets.data

import me.mattstudios.mattcore.locale.Locale
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.components.PetExperience
import me.mattstudios.triumphpets.pet.utils.PetType
import org.apache.commons.lang.StringUtils.replace
import org.bukkit.inventory.ItemStack
import java.util.UUID

/**
 * @author Matt
 */
class PetData(
        val uuid: UUID, val type: PetType,
        var name: String, var experience: PetExperience) {

    constructor(uuid: UUID, type: PetType, name: String) :
            this(uuid, type, name, PetExperience(0))

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

            line = replace(line, "{level}", experience.level.toString())
            line = replace(line, "{max_level}", "5")
            line = replace(line, "{age}", "10")
            line = replace(line, "{type}", type.typeName)
            line = replace(line, "{action}", actionMessage)
            line = replace(line, "{xp_bar}", "&a--------&c--")
            line = replace(line, "{xp}", experience.xp.toString())
            line = replace(line, "{level_xp}", "10")

            replaced.add(line)
        }

        return color(replaced)
    }
}