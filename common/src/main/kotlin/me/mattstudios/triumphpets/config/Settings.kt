package me.mattstudios.triumphpets.config

import ch.jalu.configme.Comment
import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer

object Settings : SettingsHolder {

    @JvmField
    @Comment("Whether or not should check for updates")
    val UPDATE_CHECK: Property<Boolean> = PropertyInitializer.newProperty("update-check", true)

    @JvmField
    @Comment("Change this later") // TODO fuck this
    val USE_PET_CONFIG: Property<Boolean> = PropertyInitializer.newProperty("use-pet-config", false)

}