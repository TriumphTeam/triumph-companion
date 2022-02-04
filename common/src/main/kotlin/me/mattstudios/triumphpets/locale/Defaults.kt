package me.mattstudios.triumphpets.locale

/**
 * @author Matt
 */
enum class Defaults(val path: String, private val en: String, private val pt: String) {

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
    COMMAND_CRATE_SET_SUCCESS(
            "command.admin.success.crate-set",
            "&7The crate was set successfully!",
            ""
    ),
    COMMAND_CRATE_UNSET_SUCCESS(
            "command.admin.success.crate-unset",
            "&7The crate was removed successfully!",
            ""
    ),
    COMMAND_CRATE_SET_ERROR(
            "command.admin.error.crate-set",
            "&cAn error occurred while setting the crate, please check your console!",
            ""
    ),
    COMMAND_CRATE_NO_LOOK_BLOCK(
            "command.admin.error.crate-no-look-block",
            "&cYou must be looking at a block to set the crate!",
            ""
    ),
    COMMAND_CRATE_NO_LOOK_CRATE(
            "command.admin.error.crate-no-look-crate",
            "&cYou must be looking at a crate to edit or remove it!",
            ""
    ),
    COMMAND_BLOCK_ALREADY_CRATE(
            "command.admin.error.block-already-crate",
            "&cThe block you are looking at is already a crate!",
            ""
    ),
    COMMAND_CRATE_UNSET_ERROR(
            "command.admin.error.crate-unset",
            "&cAn error occurred while removing the crate, please check your console!",
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
    ),

    /**
     * Pet crate GUI messages
     */

    // Main
    PET_CRATE_GUI_MAIN_TITLE(
            "gui.pet-crate.main.title",
            "&lPet crate options",
            ""
    ),
    PET_CRATE_GUI_MAIN_EGG_NAME(
            "gui.pet-crate.main.select-egg.name",
            "&cCrate egg",
            ""
    ),
    PET_CRATE_GUI_MAIN_EGG_LORE(
            "gui.pet-crate.main.select-egg.lore",
            "&7Select the egg you want for the crate!",
            ""
    ),
    PET_CRATE_GUI_MAIN_PARTICLE_NAME(
            "gui.pet-crate.main.select-particle.name",
            "&cCrate effect",
            ""
    ),
    PET_CRATE_GUI_MAIN_PARTICLE_LORE(
            "gui.pet-crate.main.select-particle.lore",
            "&7Select the idle particle effect you want!",
            ""
    ),
    PET_CRATE_GUI_MAIN_COMPLETE_NAME(
            "gui.pet-crate.main.complete.name",
            "&cComplete",
            ""
    ),
    PET_CRATE_GUI_MAIN_COMPLETE_LORE(
            "gui.pet-crate.main.complete.lore",
            "&7Create/Edit the crate!",
            ""
    ),

    // Egg
    PET_CRATE_GUI_EGG_TITLE(
            "gui.pet-crate.egg.title",
            "&lSelect egg",
            ""
    ),
    PET_CRATE_GUI_EGG_EGG_NAME(
            "gui.pet-crate.egg.egg.name",
            "&c{egg} egg",
            ""
    ),
    PET_CRATE_GUI_EGG_EGG_LORE(
            "gui.pet-crate.egg.egg.lore",
            "&7Click to select this egg!",
            ""
    ),

    // Effect
    PET_CRATE_GUI_EFFECT_TITLE(
            "gui.pet-crate.effect.title",
            "&lSelect effect",
            ""
    ),
    PET_CRATE_GUI_EFFECT_EFFECT_NAME(
            "gui.pet-crate.effect.effect.name",
            "&c{effect}",
            ""
    ),
    PET_CRATE_GUI_EFFECT_EFFECT_LORE(
            "gui.pet-crate.effect.effect.lore",
            "&7Click to select this effect!",
            ""
    ),

    // Common
    PET_CRATE_GUI_COMMON_BACK_NAME(
            "gui.pet-crate.common.back.name",
            "&cBack",
            ""
    ),
    PET_CRATE_GUI_COMMON_BACK_LORE(
            "gui.pet-crate.common.back.lore",
            "&7Goes to the previous menu!",
            ""
    );

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