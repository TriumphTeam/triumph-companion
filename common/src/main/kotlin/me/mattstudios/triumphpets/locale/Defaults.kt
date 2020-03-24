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
     * Pet data GUI global
     */

    PET_DATA_DISPLAY_TITLE(
            "gui.pet-data.title",
            "{name}",
            "{name}"
    ),
    PET_DATA_DISPLAY_LORE(
            "gui.pet-data.lore",
            "&8• &7Level: &c{level}&7/&c{max_level}\n" +
                    "\n" +
                    "&8• &7Age: &c{age} Days\n" +
                    "&8• &7Type: &c{type}\n" +
                    "\n" +
                    "{action}\n" +
                    "\n" +
                    "{xp_bar} &8{xp}/{level_xp}xp",
            ""
    ),
    PET_DATA_DISPLAY_ACTION_SPAWN(
            "gui.pet-data.action.spawn",
            "&cClick to spawn the pet!",
            ""
    ),
    PET_DATA_DISPLAY_ACTION_DESPAWN(
            "gui.pet-data.action.despawn",
            "&cClick to despawn the pet!",
            ""
    ),
    PET_DATA_DISPLAY_ACTION_OPTIONS(
            "gui.pet-data.action.options",
            "&cClick for pet options!",
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
    PET_LIST_GUI_NO_PET_TITLE(
            "gui.pet-list.no-pet.title",
            "&cNo Pet spawned!",
            ""
    ),
    PET_LIST_GUI_NO_PET_LORE(
            "gui.pet-list.no-pet.lore",
            "&7Spawn pet above!",
            ""
    ),
    PET_LIST_GUI_PREVIOUS(
            "gui.pet-list.previous-button.title",
            "&cPrevious page",
            ""
    ),
    PET_LIST_GUI_PREVIOUS_LORE(
            "gui.pet-list.previous-button.lore",
            "&7Click to go to the previous page!\n&7Current page: &c{page}",
            ""
    ),
    PET_LIST_GUI_NEXT(
            "gui.pet-list.next-button.title",
            "&cNext page",
            ""
    ),
    PET_LIST_GUI_NEXT_LORE(
            "gui.pet-list.next-button.lore",
            "&7Click to go to the next page!\n&7Current page: &c{page}",
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