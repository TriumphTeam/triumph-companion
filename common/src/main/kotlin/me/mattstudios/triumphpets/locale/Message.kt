package me.mattstudios.triumphpets.locale

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
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
     * Global GUI messages
     */

    @JvmField
    val GUI_CLOSE_NAME: Property<String> = newProperty(Defaults.GUI_CLOSE_NAME.getPath(),
            Defaults.GUI_CLOSE_NAME.get(PetUtils.LOCALE))

    // ------------------------------------------------------------------------------------------------- //

    /**
     * Pet list GUI messages
     */

    @JvmField
    val PET_LIST_GUI_TITLE: Property<String> = newProperty(Defaults.PET_LIST_GUI_TITLE.getPath(),
            Defaults.PET_LIST_GUI_TITLE.get(PetUtils.LOCALE))

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


    // TODO add comments
}