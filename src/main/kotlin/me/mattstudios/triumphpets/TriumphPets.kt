package me.mattstudios.triumphpets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils
import me.mattstudios.triumphpets.commands.PetCommand
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.PetUtils
import org.apache.commons.lang.StringUtils
import java.util.stream.Stream

/**
 * @author Matt
 */
class TriumphPets : MattPlugin() {

    override fun onPluginEnable() {

        setupLocale()

        startUpMessage()

        registerCommands(PetCommand(this))

    }

    private fun setupLocale() {
        //PetUtils.LOCALE = //config[Settings.LANGUAGE]
        locale.load(Message::class.java, PetUtils.LOCALE)
    }

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