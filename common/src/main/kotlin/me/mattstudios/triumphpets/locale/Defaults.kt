package me.mattstudios.triumphpets.locale

/**
 * @author Matt
 */
enum class Defaults(private var path: String, private var en: String, private var pt: String) {

    /**
     * Startup messages
     */
    STARTUP_VERSION(
            "startup.main.version",
            "&8Version:",
            ""
    ),
    STARTUP_CREATE_DATABASE_SUCCESS(
            "startup.database.db-create-success",
            "&8Database created &asuccessfully",
            ""
    ),
    STARTUP_CREATE_DATABASE_ERROR(
            "startup.database.db-create-error",
            "&cCouldn't create database file",
            ""
    ),
    STARTUP_CREATE_TABLES_ERROR(
            "startup.database.tables-create-error",
            "&cAn error occurred creating database tables!",
            ""
    ),
    STARTUP_CACHE_PETS_ERROR(
            "startup.database.pets-cache-error",
            "&cAn error occurred caching the pets data!",
            ""
    ),

    /**
     * Pet GUI messages
     */

    PET_GUI_TITLE(
            "pet.gui.title",
            "&8's Inventory",
            ""
    ),
    PET_GUI_FILTER_NAME(
            "pet.gui.filter-name",
            "&3&lFilter",
            ""
    ),
    PET_GUI_CLOSE_NAME(
            "pet.gui.close-name",
            "&c&lClose",
            ""
    ),
    PET_GUI_OPTIONS_NAME(
            "pet.gui.options-name",
            "&6&lOptions",
            ""
    );

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