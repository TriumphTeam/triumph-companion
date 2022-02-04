package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.properties.Property

/**
 * @author Matt
 */
interface PetConfig {

    /**
     * Gets a property value
     */
    operator fun <T> get(property: Property<T>): T

}