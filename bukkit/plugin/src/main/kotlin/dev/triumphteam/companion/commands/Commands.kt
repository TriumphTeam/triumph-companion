package dev.triumphteam.companion.commands

import dev.triumphteam.bukkit.commands.AbstractCommandWrapper
import dev.triumphteam.cmd.core.SubCommand
import dev.triumphteam.cmd.core.message.MessageRegistry
import dev.triumphteam.cmd.core.sender.SenderValidator
import dev.triumphteam.companion.TriumphCompanion
import dev.triumphteam.companion.user.CompanionUser
import dev.triumphteam.companion.user.ConsoleUser
import dev.triumphteam.companion.user.PlayerUser
import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import dev.triumphteam.core.feature.get
import org.bukkit.entity.Player

class Commands(plugin: TriumphCompanion, users: Users) : AbstractCommandWrapper<CompanionUser>(
    plugin = plugin,
    mapper = { sender -> if (sender is Player) users.getUser(sender.uniqueId) else users.consoleUser },
    validator = CompanionSenderValidator()
) {

    companion object Feature : ApplicationFeature<TriumphCompanion, Commands, Commands> {
        override val key = key<Commands>("commands")

        override fun install(application: TriumphCompanion, configure: Commands.() -> Unit): Commands {
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
        return true
    }
}