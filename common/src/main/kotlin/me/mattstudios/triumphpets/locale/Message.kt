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

    val STARTUP_VERSION: Property<String> = newProperty(Defaults.STARTUP_VERSION.path,
                                                        Defaults.STARTUP_VERSION.get(PetUtils.LOCALE))

    val STARTUP_CREATE_DATABASE_SUCCESS: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_SUCCESS.path,
                                                                        Defaults.STARTUP_CREATE_DATABASE_SUCCESS.get(PetUtils.LOCALE))

    val STARTUP_CREATE_DATABASE_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_DATABASE_ERROR.path,
                                                                      Defaults.STARTUP_CREATE_DATABASE_ERROR.get(PetUtils.LOCALE))

    val STARTUP_CREATE_TABLES_ERROR: Property<String> = newProperty(Defaults.STARTUP_CREATE_TABLES_ERROR.path,
                                                                    Defaults.STARTUP_CREATE_TABLES_ERROR.get(PetUtils.LOCALE))

    val STARTUP_CACHE_PETS_ERROR: Property<String> = newProperty(Defaults.STARTUP_CACHE_PETS_ERROR.path,
                                                                 Defaults.STARTUP_CACHE_PETS_ERROR.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Admin command messages
     */


    val COMMAND_GIVE_SUCCESS: Property<String> = newProperty(Defaults.COMMAND_GIVE_SUCCESS.path,
                                                             Defaults.COMMAND_GIVE_SUCCESS.get(PetUtils.LOCALE))

    val COMMAND_CRATE_SET_SUCCESS: Property<String> = newProperty(Defaults.COMMAND_CRATE_SET_SUCCESS.path,
                                                                  Defaults.COMMAND_CRATE_SET_SUCCESS.get(PetUtils.LOCALE))

    val COMMAND_CRATE_SET_ERROR: Property<String> = newProperty(Defaults.COMMAND_CRATE_SET_ERROR.path,
                                                                  Defaults.COMMAND_CRATE_SET_ERROR.get(PetUtils.LOCALE))

    val COMMAND_CRATE_UNSET_SUCCESS: Property<String> = newProperty(Defaults.COMMAND_CRATE_UNSET_SUCCESS.path,
                                                                    Defaults.COMMAND_CRATE_UNSET_SUCCESS.get(PetUtils.LOCALE))

    val COMMAND_CRATE_UNSET_ERROR: Property<String> = newProperty(Defaults.COMMAND_CRATE_UNSET_ERROR.path,
                                                                    Defaults.COMMAND_CRATE_UNSET_ERROR.get(PetUtils.LOCALE))

    val COMMAND_CRATE_NO_LOOK_BLOCK: Property<String> = newProperty(Defaults.COMMAND_CRATE_NO_LOOK_BLOCK.path,
                                                                  Defaults.COMMAND_CRATE_NO_LOOK_BLOCK.get(PetUtils.LOCALE))

    val COMMAND_CRATE_NO_LOOK_CRATE: Property<String> = newProperty(Defaults.COMMAND_CRATE_NO_LOOK_CRATE.path,
                                                                    Defaults.COMMAND_CRATE_NO_LOOK_CRATE.get(PetUtils.LOCALE))

    val COMMAND_BLOCK_ALREADY_CRATE: Property<String> = newProperty(Defaults.COMMAND_BLOCK_ALREADY_CRATE.path,
                                                                    Defaults.COMMAND_BLOCK_ALREADY_CRATE.get(PetUtils.LOCALE))

    val COMMAND_GIVE_NO_PLAYER: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PLAYER.path,
                                                               Defaults.COMMAND_GIVE_NO_PLAYER.get(PetUtils.LOCALE))

    val COMMAND_GIVE_NO_PET: Property<String> = newProperty(Defaults.COMMAND_GIVE_NO_PET.path,
                                                            Defaults.COMMAND_GIVE_NO_PET.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet data GUI global
     */


    val PET_DATA_DISPLAY_TITLE: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_TITLE.path,
                                                               Defaults.PET_DATA_DISPLAY_TITLE.get(PetUtils.LOCALE))

    val PET_DATA_DISPLAY_LORE: Property<List<String>> = newListProperty(Defaults.PET_DATA_DISPLAY_LORE.path,
                                                                        Defaults.PET_DATA_DISPLAY_LORE.get(PetUtils.LOCALE).split("\n"))

    val PET_DATA_DISPLAY_ACTION_SPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.path,
                                                                      Defaults.PET_DATA_DISPLAY_ACTION_SPAWN.get(PetUtils.LOCALE))

    val PET_DATA_DISPLAY_ACTION_DESPAWN: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.path,
                                                                        Defaults.PET_DATA_DISPLAY_ACTION_DESPAWN.get(PetUtils.LOCALE))

    val PET_DATA_DISPLAY_ACTION_OPTIONS: Property<String> = newProperty(Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.path,
                                                                        Defaults.PET_DATA_DISPLAY_ACTION_OPTIONS.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet list GUI messages
     */

    val PET_LIST_GUI_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_TITLE.path,
                                                           Defaults.PET_LIST_GUI_TITLE.get(PetUtils.LOCALE))

    val PET_LIST_GUI_NO_PET_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_NO_PET_TITLE.path,
                                                                  Defaults.PET_LIST_GUI_NO_PET_TITLE.get(PetUtils.LOCALE))

    val PET_LIST_GUI_NO_PET_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NO_PET_LORE.path,
                                                                           Defaults.PET_LIST_GUI_NO_PET_LORE.get(PetUtils.LOCALE))

    val PET_LIST_GUI_PREVIOUS: Property<String> = newProperty(Defaults.PET_LIST_GUI_PREVIOUS.path,
                                                              Defaults.PET_LIST_GUI_PREVIOUS.get(PetUtils.LOCALE))

    val PET_LIST_GUI_PREVIOUS_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_PREVIOUS_LORE.path,
                                                                             Defaults.PET_LIST_GUI_PREVIOUS_LORE.get(PetUtils.LOCALE).split("\n"))

    val PET_LIST_GUI_NEXT: Property<String> = newProperty(Defaults.PET_LIST_GUI_NEXT.path,
                                                          Defaults.PET_LIST_GUI_NEXT.get(PetUtils.LOCALE))

    val PET_LIST_GUI_NEXT_LORE: Property<List<String>> = newListProperty(Defaults.PET_LIST_GUI_NEXT_LORE.path,
                                                                         Defaults.PET_LIST_GUI_NEXT_LORE.get(PetUtils.LOCALE).split("\n"))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet GUI messages
     */


    val PET_GUI_TITLE: Property<String> = newProperty(Defaults.PET_GUI_TITLE.path,
                                                      Defaults.PET_GUI_TITLE.get(PetUtils.LOCALE))

    val PET_GUI_FILTER_NAME: Property<String> = newProperty(Defaults.PET_GUI_FILTER_NAME.path,
                                                            Defaults.PET_GUI_FILTER_NAME.get(PetUtils.LOCALE))

    val PET_GUI_OPTIONS_NAME: Property<String> = newProperty(Defaults.PET_GUI_OPTIONS_NAME.path,
                                                             Defaults.PET_GUI_OPTIONS_NAME.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet crate GUI messages
     */

    // Main
    val PET_CRATE_GUI_MAIN_TITLE: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_TITLE.path,
                                                                 Defaults.PET_CRATE_GUI_MAIN_TITLE.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_MAIN_EGG_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_EGG_NAME.path,
                                                                    Defaults.PET_CRATE_GUI_MAIN_EGG_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_MAIN_EGG_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_MAIN_EGG_LORE.path,
                                                                              Defaults.PET_CRATE_GUI_MAIN_EGG_LORE.get(PetUtils.LOCALE).split("\n"))

    val PET_CRATE_GUI_MAIN_PARTICLE_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_PARTICLE_NAME.path,
                                                                         Defaults.PET_CRATE_GUI_MAIN_PARTICLE_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_MAIN_PARTICLE_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_MAIN_PARTICLE_LORE.path,
                                                                                   Defaults.PET_CRATE_GUI_MAIN_PARTICLE_LORE.get(PetUtils.LOCALE).split("\n"))

    val PET_CRATE_GUI_MAIN_COMPLETE_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_MAIN_COMPLETE_NAME.path,
                                                                         Defaults.PET_CRATE_GUI_MAIN_COMPLETE_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_MAIN_COMPLETE_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_MAIN_COMPLETE_LORE.path,
                                                                                   Defaults.PET_CRATE_GUI_MAIN_COMPLETE_LORE.get(PetUtils.LOCALE).split("\n"))

    // Egg
    val PET_CRATE_GUI_EGG_TITLE: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EGG_TITLE.path,
                                                                Defaults.PET_CRATE_GUI_EGG_TITLE.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_EGG_EGG_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EGG_EGG_NAME.path,
                                                                   Defaults.PET_CRATE_GUI_EGG_EGG_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_EGG_EGG_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_EGG_EGG_LORE.path,
                                                                             Defaults.PET_CRATE_GUI_EGG_EGG_LORE.get(PetUtils.LOCALE).split("\n"))

    // Effect
    val PET_CRATE_GUI_EFFECT_TITLE: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EFFECT_TITLE.path,
                                                                Defaults.PET_CRATE_GUI_EFFECT_TITLE.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_EFFECT_EFFECT_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_EFFECT_EFFECT_NAME.path,
                                                                   Defaults.PET_CRATE_GUI_EFFECT_EFFECT_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_EFFECT_EFFECT_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_EFFECT_EFFECT_LORE.path,
                                                                             Defaults.PET_CRATE_GUI_EFFECT_EFFECT_LORE.get(PetUtils.LOCALE).split("\n"))


    // Common
    val PET_CRATE_GUI_COMMON_BACK_NAME: Property<String> = newProperty(Defaults.PET_CRATE_GUI_COMMON_BACK_NAME.path,
                                                                       Defaults.PET_CRATE_GUI_COMMON_BACK_NAME.get(PetUtils.LOCALE))

    val PET_CRATE_GUI_COMMON_BACK_LORE: Property<List<String>> = newListProperty(Defaults.PET_CRATE_GUI_COMMON_BACK_LORE.path,
                                                                                 Defaults.PET_CRATE_GUI_COMMON_BACK_LORE.get(PetUtils.LOCALE).split("\n"))
    // ------------------------------------------------------------------------------------------------- //

    /**
     * Registers the commands for the locale file
     */
    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("startup", "Messages that will appear during the startup of the plugin.")

        conf.setComment("command", "\n", "Messages that will be seen when performing commands.")
        conf.setComment("command.admin", "Messages related to admin commands.")

        conf.setComment("gui", "\n", "Change the following messages to change how the GUI looks.")

        conf.setComment("gui.pet-data", "Messages displayed when viewing the pet's data.")
        conf.setComment("gui.pet-list", "Messages for the command \"/pets\".")
        conf.setComment("gui.pet-crate", "Messages displayed when opening the crate gui.")
        conf.setComment("gui.pet", "Messages for the Pet inventory.")
    }
}