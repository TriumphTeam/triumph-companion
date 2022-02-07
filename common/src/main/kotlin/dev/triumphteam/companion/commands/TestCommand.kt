package dev.triumphteam.companion.commands

import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.companion.user.PlayerUser

@Command("companion")
class TestCommand {

    fun PlayerUser.test() {
        println("Hello!")
    }

}