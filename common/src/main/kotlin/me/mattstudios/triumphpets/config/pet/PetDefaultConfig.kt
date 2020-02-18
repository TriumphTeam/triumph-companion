package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.properties.Property
import me.mattstudios.triumphpets.config.pet.PetConfig

/**
 * @author Matt
 */
class PetDefaultConfig : PetConfig {

    override fun get(property: Property<*>): Any {
        return property.defaultValue
    }

}