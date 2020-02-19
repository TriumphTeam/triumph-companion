package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.properties.Property

/**
 * @author Matt
 */
class PetDefaultConfig : PetConfig {

    override operator fun <T> get(property: Property<T>): T {
        return property.defaultValue
    }

}