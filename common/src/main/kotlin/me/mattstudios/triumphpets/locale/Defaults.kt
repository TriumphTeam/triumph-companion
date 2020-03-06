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
     * Admin commands
     */

    COMMAND_GIVE_SUCCESS(
            "command.admin.success.give-success",
            "&7Pet was given to &a{player} &7successfully!",
            ""
    ),
    COMMAND_GIVE_NO_PLAYER(
            "command.admin.error.give-no-player",
            "&cThe selected player doesn't exist or is not online!",
            ""
    ),
    COMMAND_GIVE_NO_PET(
            "command.admin.error.give-no-pet",
            "&cThe Pet you introduced is not a valid one!",
            ""
    ),

    /**
     * Global GUI messages
     */

    GUI_CLOSE_NAME(
            "gui.global.close-name",
            "&c&lClose",
            ""
    ),

    /**
     * Pet list GUI messages
     */

    PET_LIST_GUI_TITLE(
            "gui.pet-list.title",
            "&cPets",
            ""
    ),

    /**
     * Pet GUI messages
     */

    PET_GUI_TITLE(
            "gui.pet.title",
            "&8's Inventory",
            ""
    ),
    PET_GUI_FILTER_NAME(
            "gui.pet.filter-name",
            "&3&lFilter",
            ""
    ),
    PET_GUI_OPTIONS_NAME(
            "gui.pet.options-name",
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