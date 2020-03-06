package me.mattstudios.triumphpets

import com.google.common.primitives.Ints
import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils
import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.mf.base.components.CompletionResolver
import me.mattstudios.mf.base.components.ParameterResolver
import me.mattstudios.mf.base.components.TypeResult
import me.mattstudios.triumphpets.commands.admin.PetGiveCommand
import me.mattstudios.triumphpets.commands.player.PetsCommand
import me.mattstudios.triumphpets.config.Settings
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetDefaultConfig
import me.mattstudios.triumphpets.config.pet.PetFileConfig
import me.mattstudios.triumphpets.listeners.PetListeners
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.manager.PetManager
import me.mattstudios.triumphpets.pet.utils.Experience
import me.mattstudios.triumphpets.pet.utils.PetType
import me.mattstudios.triumphpets.pet.utils.PetUtils
import java.util.regex.Pattern
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
    override fun enable() {
        setupConfig()
        setupLocale()

        startUpMessage()

        petManager = PetManager(this)

        setupCommands()
        registerListeners(PetListeners(this))

        Experience.load(config)
    }

    /**
     * On plugin disable
     */
    override fun disable() {
        petManager.petController.removeAll()
    }

    /**
     * Sets up the config related stuff
     */
    private fun setupConfig() {
        config.load(Settings::class.java)

        petConfig = if (config[Settings.USE_PET_CONFIG]) PetFileConfig(this) else PetDefaultConfig()
    }

    /**
     * Sets up the locale of the plugin
     */
    private fun setupLocale() {
        PetUtils.LOCALE = config[Settings.LANGUAGE]
        locale.load(Message::class.java, PetUtils.LOCALE)
    }

    /**
     * Sets up everything related to commands
     */
    private fun setupCommands() {
        val pattern = Pattern.compile("_(\\d+)_")
        val matcher = pattern.matcher(getServerVersion())

        var version: Int? = null

        while (matcher.find()) {
            @Suppress("UnstableApiUsage")
            version = Ints.tryParse(matcher.group(1))
        }

        commandManager.parameterHandler.register(PetType::class.java, ParameterResolver { argument ->
            if (version == null || argument == null) return@ParameterResolver TypeResult(argument)
            val petType = PetType.values().toList().filter { it.version <= version }.find { it.name.equals(argument.toString(), true) }
            return@ParameterResolver TypeResult(petType, argument)
        })

        registerCompletion("#pets", CompletionResolver {
            if (version == null) return@CompletionResolver listOf("")
            return@CompletionResolver PetType.values().toList().filter { it.version <= version }.map { it.name }
        })

        registerCommands(
                PetGiveCommand(this),
                PetsCommand(this)
        )
    }

    /**
     * Just the startup message
     */
    private fun startUpMessage() {
        Stream.of(
                "",
                "&3&l█▀█ █▀▀ ▀█▀ █▀",
                "&3&l█▀▀ ██▄ ░█░ ▄█",
                "${locale.getMessageRaw(Message.STARTUP_VERSION)} &c1.0 &8(&c${PetUtils.LOCALE}&8)"
        ).forEach { MessageUtils.info(MessageUtils.color(it)) }
    }

}