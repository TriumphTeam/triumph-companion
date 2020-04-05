package me.mattstudios.triumphpets.config

import ch.jalu.configme.Comment
import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.configurationdata.CommentsConfiguration
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer.newProperty

object Settings : SettingsHolder {

    @JvmField
    @Comment("Whether or not should check for updates")
    val UPDATE_CHECK: Property<Boolean> = newProperty("update-check", true)

    @JvmField
    @Comment("\n", "Whether or not should check for updates") // TODO Change comment
    val LANGUAGE: Property<String> = newProperty("language", "en_US")

    /**
     * PET EXPERIENCE GAIN
     */

    @JvmField
    val EXPERIENCE_BASIC_MIN: Property<Int> = newProperty("pet.experience.basic.min", 0)

    @JvmField
    val EXPERIENCE_BASIC_MAX: Property<Int> = newProperty("pet.experience.basic.max", 1)

    @JvmField
    val EXPERIENCE_ORES_MIN: Property<Int> = newProperty("pet.experience.ores.min", 0)

    @JvmField
    val EXPERIENCE_ORES_MAX: Property<Int> = newProperty("pet.experience.ores.max", 2)

    @JvmField
    val EXPERIENCE_INGOTS_MIN: Property<Int> = newProperty("pet.experience.ingots.min", 1)

    @JvmField
    val EXPERIENCE_INGOTS_MAX: Property<Int> = newProperty("pet.experience.ingots.max", 2)

    @JvmField
    val EXPERIENCE_UNSTACKABLE_MIN: Property<Int> = newProperty("pet.experience.unstackable.min", 1)

    @JvmField
    val EXPERIENCE_UNSTACKABLE_MAX: Property<Int> = newProperty("pet.experience.unstackable.max", 3)

    @JvmField
    val EXPERIENCE_RARE_MIN: Property<Int> = newProperty("pet.experience.rare.min", 3)

    @JvmField
    val EXPERIENCE_RARE_MAX: Property<Int> = newProperty("pet.experience.rare.max", 5)

    @JvmField
    val EXPERIENCE_SPECIAL_MIN: Property<Int> = newProperty("pet.experience.special.min", 5)

    @JvmField
    val EXPERIENCE_SPECIAL_MAX: Property<Int> = newProperty("pet.experience.special.max", 10)

    @JvmField
    @Comment("\n", "Change this later") // TODO Change comment
    val USE_PET_CONFIG: Property<Boolean> = newProperty("use-pet-config", false)

    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("pet", "\n")
    }

}