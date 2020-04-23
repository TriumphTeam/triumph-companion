package me.mattstudios.triumphpets

import com.google.common.primitives.Ints
import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.MessageUtils.info
import me.mattstudios.mattcore.utils.NmsUtils.getServerVersion
import me.mattstudios.mf.base.components.CompletionResolver
import me.mattstudios.mf.base.components.ParameterResolver
import me.mattstudios.mf.base.components.TypeResult
import me.mattstudios.triumphpets.commands.admin.CrateCommand
import me.mattstudios.triumphpets.commands.admin.PetGiveCommand
import me.mattstudios.triumphpets.commands.player.PetsCommand
import me.mattstudios.triumphpets.config.Settings
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetDefaultConfig
import me.mattstudios.triumphpets.config.pet.PetFileConfig
import me.mattstudios.triumphpets.listeners.CrateListeners
import me.mattstudios.triumphpets.listeners.PetListeners
import me.mattstudios.triumphpets.listeners.PlayerListeners
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.managers.PetManager
import me.mattstudios.triumphpets.pet.utils.Experience
import me.mattstudios.triumphpets.pet.utils.PetType
import me.mattstudios.triumphpets.pet.utils.PetUtils
import me.mattstudios.triumphpets.util.Utils.getPluginStartup
import java.util.regex.Pattern
import java.util.stream.Stream


/**
 * @author Matt
 */
class TriumphPets : MattPlugin() {

    lateinit var petManager: PetManager
        private set
    lateinit var petConfig: PetConfig
        private set

    /**
     * On plugin enable
     */
    override fun enable() {
        setupConfig()
        setupLocale()

        startUpMessage()

        Experience.load(config)

        petManager = PetManager(this)

        setupCommands()
        setupListeners()
    }

    /**
     * On plugin disable
     */
    override fun disable() {
        petManager.disable()
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

        registerCompletion("#crate-type") { listOf("set", "edit", "unset") }

        registerCommands(
                PetGiveCommand(this),
                CrateCommand(this),

                PetsCommand(this)
                        )
    }

    /**
     * Registers all the listeners
     */
    private fun setupListeners() {
        registerListeners(
                PetListeners(this),
                PlayerListeners(this),
                CrateListeners(this)
                         )
    }

    /**
     * Just the startup message
     */
    private fun startUpMessage() {
        Stream.of(
                "",
                getPluginStartup(),
                "${locale.getMessageRaw(Message.STARTUP_VERSION)} &c${description.version} &8(&c${PetUtils.LOCALE}&8)"
                 ).forEach { info(color(it)) }
    }

}