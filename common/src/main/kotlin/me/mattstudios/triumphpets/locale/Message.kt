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
    val STARTUP_VERSION: Property<String> = newProperty(Defaults.STARTUP_VERSION.getPath(),
                                                        Defaults.STARTUP_VERSION.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_DATABASE_SUCCESS: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_SUCCESS.getPath(),
                                                                        Defaults.STARTUP_CREATE_DATABASE_SUCCESS.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_DATABASE_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_ERROR.getPath(),
                                                                      Defaults.STARTUP_CREATE_DATABASE_ERROR.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CREATE_TABLES_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_TABLES_ERROR.getPath(),
                                                                    Defaults.STARTUP_CREATE_TABLES_ERROR.get(PetUtils.LOCALE))

    @JvmField
    val STARTUP_CACHE_PETS_ERROR: Property<String> = newProperty(Defaults.STARTUP_CACHE_PETS_ERROR.getPath(),
                                                                 Defaults.STARTUP_CACHE_PETS_ERROR.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Admin command messages
     */

    @JvmField
    val COMMAND_GIVE_NO_PLAYER: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PLAYER.getPath(),
                                                               Defaults.COMMAND_GIVE_NO_PLAYER.get(PetUtils.LOCALE))

    @JvmField
    val COMMAND_GIVE_NO_PET: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PET.getPath(),
                                                            Defaults.COMMAND_GIVE_NO_PET.get(PetUtils.LOCALE))

    @JvmField
    val COMMAND_GIVE_SUCCESS: Property<String> = newProperty(Defaults.COMMAND_GIVE_SUCCESS.getPath(),
                                                             Defaults.COMMAND_GIVE_SUCCESS.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet data GUI global
     */

    @JvmField
    val PET_DATA_DISPLAY_TITLE: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_TITLE.getPath(),
                                                               Defaults.PET_DATA_DISPLAY_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_LORE: Property<List<String>> = newListProperty(Defaults.PET_DATA_DISPLAY_LORE.getPath(),
                                                                        Defaults.PET_DATA_DISPLAY_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_SPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.getPath(),
                                                                      Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_DESPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.getPath(),
                                                                        Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.get(PetUtils.LOCALE))

    @JvmField
    val PET_DATA_DISPLAY_ACTION_OPTIONS: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.getPath(),
                                                                        Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet list GUI messages
     */

    @JvmField
    val PET_LIST_GUI_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_TITLE.getPath(),
                                                           Defaults.PET_LIST_GUI_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NO_PET_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_NO_PET_TITLE.getPath(),
                                                                  Defaults.PET_LIST_GUI_NO_PET_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NO_PET_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NO_PET_LORE.getPath(),
                                                                           Defaults.PET_LIST_GUI_NO_PET_LORE.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_PREVIOUS: Property<String> = newProperty(Defaults.PET_LIST_GUI_PREVIOUS.getPath(),
                                                              Defaults.PET_LIST_GUI_PREVIOUS.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_PREVIOUS_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_PREVIOUS_LORE.getPath(),
                                                                             Defaults.PET_LIST_GUI_PREVIOUS_LORE.get(PetUtils.LOCALE).split("\n"))

    @JvmField
    val PET_LIST_GUI_NEXT: Property<String> = newProperty(Defaults.PET_LIST_GUI_NEXT.getPath(),
                                                          Defaults.PET_LIST_GUI_NEXT.get(PetUtils.LOCALE))

    @JvmField
    val PET_LIST_GUI_NEXT_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NEXT_LORE.getPath(),
                                                                         Defaults.PET_LIST_GUI_NEXT_LORE.get(PetUtils.LOCALE).split("\n"))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet GUI messages
     */

    @JvmField
    val PET_GUI_TITLE: Property<String> = newProperty(Defaults.PET_GUI_TITLE.getPath(),
                                                      Defaults.PET_GUI_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_FILTER_NAME: Property<String> = newProperty(Defaults.PET_GUI_FILTER_NAME.getPath(),
                                                            Defaults.PET_GUI_FILTER_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_OPTIONS_NAME: Property<String> = newProperty(Defaults.PET_GUI_OPTIONS_NAME.getPath(),
                                                             Defaults.PET_GUI_OPTIONS_NAME.get(PetUtils.LOCALE))


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