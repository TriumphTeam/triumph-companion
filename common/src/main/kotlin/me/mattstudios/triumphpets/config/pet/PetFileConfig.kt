package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.SettingsManager
import ch.jalu.configme.SettingsManagerBuilder
import ch.jalu.configme.configurationdata.ConfigurationDataBuilder
import ch.jalu.configme.properties.Property
import me.mattstudios.mattcore.MattPlugin
import java.io.File

/**
 * @author Matt
 */
class PetFileConfig(plugin: MattPlugin) : PetConfig {

    // The settings holder of the config
    private var settingsManager: SettingsManager = SettingsManagerBuilder.withYamlFile(
            File(plugin.dataFolder, "pet-config.yml"))
            .useDefaultMigrationService()
            .configurationData(ConfigurationDataBuilder.createConfiguration(PetProperty::class.java))
            .create()


    /**
     * Gets a property value
     */
    override operator fun <T> get(property: Property<T>): T {
        return settingsManager.getProperty(property)
    }

}