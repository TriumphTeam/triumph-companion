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
     * PET GUI MESSAGES
     */

    @JvmField
    val PET_GUI_TITLE: Property<String> = newProperty(Defaults.PET_GUI_TITLE.getPath(), Defaults.PET_GUI_TITLE.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_FILTER_NAME: Property<String> = newProperty(Defaults.PET_GUI_FILTER_NAME.getPath(), Defaults.PET_GUI_FILTER_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_CLOSE_NAME: Property<String> =
            newProperty(Defaults.PET_GUI_CLOSE_NAME.getPath(),
                    Defaults.PET_GUI_CLOSE_NAME.get(PetUtils.LOCALE))

    @JvmField
    val PET_GUI_OPTIONS_NAME: Property<String> =
            newProperty(Defaults.PET_GUI_OPTIONS_NAME.getPath(),
                    Defaults.PET_GUI_OPTIONS_NAME.get(PetUtils.LOCALE))

}