package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.properties.Property

/**
 * @author Matt
 */
interface PetConfig {

    /**
     * Gets a property value
     */
    fun get(property: Property<*>): Any

}