package dev.triumphteam.companion.commands

import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.companion.user.CompanionUser
import net.kyori.adventure.text.Component

@Command("companion")
class TestCommand : BaseCommand() {

    @Default
    fun CompanionUser.test() {
        sendMessage(Component.text("Hello!"))
    }
}