package dev.triumphteam.companion.commands

import dev.triumphteam.bukkit.commands.AbstractCommandWrapper
import dev.triumphteam.cmd.bukkit.message.BukkitMessageKey
import dev.triumphteam.cmd.core.SubCommand
import dev.triumphteam.cmd.core.message.MessageRegistry
import dev.triumphteam.cmd.core.message.context.DefaultMessageContext
import dev.triumphteam.cmd.core.sender.SenderValidator
import dev.triumphteam.companion.CompanionBukkit
import dev.triumphteam.companion.user.CompanionUser
import dev.triumphteam.companion.user.ConsoleUser
import dev.triumphteam.companion.user.PlayerUser
import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import dev.triumphteam.core.feature.get
import org.bukkit.entity.Player

class Commands(plugin: CompanionBukkit, users: Users) : AbstractCommandWrapper<CompanionUser>(
    plugin = plugin,
    mapper = { sender -> if (sender is Player) users.getUser(sender.uniqueId) else users.consoleUser },
    validator = CompanionSenderValidator()
) {

    companion object Feature : ApplicationFeature<CompanionBukkit, Commands, Commands> {
        override val key = key<Commands>("commands")

        override fun install(application: CompanionBukkit, configure: Commands.() -> Unit): Commands {
            val users = application[Users]
            return Commands(application, users).apply(configure)
        }
    }
}

class CompanionSenderValidator : SenderValidator<CompanionUser> {

    override fun getAllowedSenders(): Set<Class<out CompanionUser>> {
        return setOf(CompanionUser::class.java, PlayerUser::class.java, ConsoleUser::class.java)
    }

    override fun validate(
        messageRegistry: MessageRegistry<CompanionUser>,
        subCommand: SubCommand<CompanionUser>,
        sender: CompanionUser
    ): Boolean {
        val senderClass = subCommand.senderType

        return when {
            PlayerUser::class.java.isAssignableFrom(senderClass) && sender !is PlayerUser -> {
                messageRegistry.sendMessage(
                    BukkitMessageKey.PLAYER_ONLY,
                    sender,
                    DefaultMessageContext(subCommand.parentName, subCommand.name)
                )
                false
            }

            ConsoleUser::class.java.isAssignableFrom(senderClass) && sender !is ConsoleUser -> {
                messageRegistry.sendMessage(
                    BukkitMessageKey.CONSOLE_ONLY,
                    sender,
                    DefaultMessageContext(subCommand.parentName, subCommand.name)
                )
                false
            }

            else -> true
        }
    }
}