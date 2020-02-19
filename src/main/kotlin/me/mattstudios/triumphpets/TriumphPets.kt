package me.mattstudios.triumphpets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils
import me.mattstudios.triumphpets.commands.PetCommand
import me.mattstudios.triumphpets.config.Settings
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetDefaultConfig
import me.mattstudios.triumphpets.config.pet.PetFileConfig
import me.mattstudios.triumphpets.listeners.PetListener
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.manager.PetManager
import me.mattstudios.triumphpets.pet.utils.PetUtils
import org.apache.commons.lang.StringUtils
import java.util.stream.Stream

/**
 * @author Matt
 */
class TriumphPets : MattPlugin() {

    lateinit var petManager: PetManager
    lateinit var petConfig: PetConfig

    /**
     * On plugin enable
     */
    override fun onPluginEnable() {

        setupConfig()
        setupLocale()

        startUpMessage()

        petManager = PetManager(this)

        registerCommands(PetCommand(this))
        registerListeners(PetListener(this))

    }

    /**
     * On plugin disable
     */
    override fun onPluginDisable() {
        petManager.petController.removeAll()
    }

    private fun setupConfig() {
        config.load(Settings::class.java)

        petConfig = if (config[Settings.USE_PET_CONFIG]) PetFileConfig(this) else PetDefaultConfig()
    }

    /**
     * Sets up the locale of the plugin
     */
    private fun setupLocale() {
        //PetUtils.LOCALE = //config[Settings.LANGUAGE]
        locale.load(Message::class.java, PetUtils.LOCALE)
    }

    /**
     * Just the startup message
     */
    private fun startUpMessage() {
        Stream.of(
                "",
                "&3&l█▀█ █▀▀ ▀█▀ █▀",
                "&3&l█▀▀ ██▄ ░█░ ▄█",
                "       &8Version: &c1.0 &8(&a${PetUtils.LOCALE}&8)",
                ""
        ).forEach { MessageUtils.info(StringUtils.center(MessageUtils.color(it), 53)) }
    }

}