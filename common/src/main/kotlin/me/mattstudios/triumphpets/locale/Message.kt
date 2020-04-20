package me.mattstudios.triumphpets.locale

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.configurationdata.CommentsConfiguration
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer.newListProperty
import ch.jalu.configme.properties.PropertyInitializer.newProperty
import me.mattstudios.triumphpets.pet.utils.PetUtils

/**
 * @author Matt
 */

object Message : SettingsHolder {

    /**
     * Startup Messages
     */

    @JvmField
    val STARTUP_VERSION: Property<String> = newProperty(Defaults.STARTUP_VERSION.path,
            Defaults.STARTUP_VERSION.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_DATABASE_SUCCESS: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_SUCCESS.path,
            Defaults.STARTUP_CREATE_DATABASE_SUCCESS.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_DATABASE_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_ERROR.path,
            Defaults.STARTUP_CREATE_DATABASE_ERROR.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_TABLES_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_TABLES_ERROR.path,
            Defaults.STARTUP_CREATE_TABLES_ERROR.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CACHE_PETS_ERROR: Property<String> = newProperty(Defaults.STARTUP_CACHE_PETS_ERROR.path,
            Defaults.STARTUP_CACHE_PETS_ERROR.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Admin command messages
     */

    @JvmField
    val COMMAND_GIVE_NO_PLAYER: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PLAYER.path,
            Defaults.COMMAND_GIVE_NO_PLAYER.get(PetUtils.LOCALE))

    @JvmField
    val COMMAND_GIVE_NO_PET: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PET.path,
            Defaults.COMMAND_GIVE_NO_PET.get(PetUtils.LOCALE))

    @JvmField
    val COMMAND_GIVE_SUCCESS: Property<String> = newProperty(Defaults.COMMAND_GIVE_SUCCESS.path,
            Defaults.COMMAND_GIVE_SUCCESS.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet data GUI global
     */

    @JvmField
    val PET_DATA_DISPLAY_TITLE: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_TITLE.path,
            Defaults.PET_DATA_DISPLAY_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_LORE: Property<List<String>> = newListProperty(Defaults.PET_DATA_DISPLAY_LORE.path,
            Defaults.PET_DATA_DISPLAY_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_SPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.path,
            Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_DESPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.path,
            Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_OPTIONS: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.path,
            Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet list GUI messages
     */

    @JvmField
    val PET_LIST_GUI_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_TITLE.path,
            Defaults.PET_LIST_GUI_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NO_PET_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_NO_PET_TITLE.path,
            Defaults.PET_LIST_GUI_NO_PET_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NO_PET_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NO_PET_LORE.path,
            Defaults.PET_LIST_GUI_NO_PET_LORE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_PREVIOUS: Property<String> = newProperty(Defaults.PET_LIST_GUI_PREVIOUS.path,
            Defaults.PET_LIST_GUI_PREVIOUS.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_PREVIOUS_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_PREVIOUS_LORE.path,
            Defaults.PET_LIST_GUI_PREVIOUS_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_LIST_GUI_NEXT: Property<String> = newProperty(Defaults.PET_LIST_GUI_NEXT.path,
            Defaults.PET_LIST_GUI_NEXT.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NEXT_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NEXT_LORE.path,
            Defaults.PET_LIST_GUI_NEXT_LORE.get(PetUtils.LOCALE).split("\n"))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet GUI messages
     */

    @JvmField
    val PET_GUI_TITLE: Property<String> = newProperty(Defaults.PET_GUI_TITLE.path,
            Defaults.PET_GUI_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_FILTER_NAME: Property<String> = newProperty(Defaults.PET_GUI_FILTER_NAME.path,
            Defaults.PET_GUI_FILTER_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_OPTIONS_NAME: Property<String> = newProperty(Defaults.PET_GUI_OPTIONS_NAME.path,
            Defaults.PET_GUI_OPTIONS_NAME.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet crate GUI messages
     */

    // Main
    @JvmField
    val PET_CRATE_GUI_MAIN_TITLE: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_TITLE.path,
            Defaults.PET_CRATE_GUI_MAIN_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_MAIN_EGG_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_EGG_NAME.path,
            Defaults.PET_CRATE_GUI_MAIN_EGG_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_MAIN_EGG_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_MAIN_EGG_LORE.path,
            Defaults.PET_CRATE_GUI_MAIN_EGG_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_CRATE_GUI_MAIN_PARTICLE_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_PARTICLE_NAME.path,
            Defaults.PET_CRATE_GUI_MAIN_PARTICLE_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_MAIN_PARTICLE_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_MAIN_PARTICLE_LORE.path,
            Defaults.PET_CRATE_GUI_MAIN_PARTICLE_LORE.get(PetUtils.LOCALE).split("\n"))

    // Egg
    @JvmField
    val PET_CRATE_GUI_EGG_TITLE: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EGG_TITLE.path,
            Defaults.PET_CRATE_GUI_EGG_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_EGG_BACK_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EGG_BACK_NAME.path,
            Defaults.PET_CRATE_GUI_EGG_BACK_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_EGG_BACK_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_EGG_BACK_LORE.path,
            Defaults.PET_CRATE_GUI_EGG_BACK_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_CRATE_GUI_EGG_EGG_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EGG_EGG_NAME.path,
            Defaults.PET_CRATE_GUI_EGG_EGG_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_CRATE_GUI_EGG_EGG_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_EGG_EGG_LORE.path,
            Defaults.PET_CRATE_GUI_EGG_EGG_LORE.get(PetUtils.LOCALE).split("\n"))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Registers the commands for the locale file
     */
    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("startup", "Messages that will appear during the startup of the plugin.")
        conf.setComment("command", "\n", "Messages that will be seen when performing commands.")

        conf.setComment("gui", "\n", "Change the following messages to change how the GUI looks.")

        conf.setComment("gui.pet-list", "Messages for the command \"/pets\".")
        conf.setComment("gui.pet-list.no-pet", "Messages that will be displayed on the no pet item in the GUI.")
        conf.setComment("gui.pet-list.previous-button", "Messages that will be displayed on the previous item in the GUI.")
        conf.setComment("gui.pet-list.next-button", "Messages that will be displayed on the next item in the GUI.")

        conf.setComment("gui.pet", "Messages for the Pet inventory.")
    }
}