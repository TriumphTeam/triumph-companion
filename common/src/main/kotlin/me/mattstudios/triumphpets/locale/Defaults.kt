package me.mattstudios.triumphpets.locale

/**
 * @author Matt
 */
enum class Defaults(private var path: String, private var en: String, private var pt: String) {

    PET_GUI_TITLE("pet.gui.title", "&8's Inventory", ""),
    PET_GUI_FILTER_NAME("pet.gui.filter-name", "&3&lFilter", ""),
    PET_GUI_CLOSE_NAME("pet.gui.close-name", "&c&lClose", ""),
    PET_GUI_OPTIONS_NAME("pet.gui.options-name", "&6&lOptions", "");

    /**
     * Get's the path to the message
     */
    fun getPath(): String {
        return path
    }

    /**
     * Get's the message in that language
     */
    fun get(locale: String): String {
        return when (locale) {
            "en_US" -> en
            "pt_BR" -> pt
            else -> en
        }
    }

}