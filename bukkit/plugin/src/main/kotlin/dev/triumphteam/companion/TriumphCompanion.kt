package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.companion.commands.Commands
import dev.triumphteam.companion.commands.TestCommand
import dev.triumphteam.companion.user.BukkitConsoleUser
import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.BukkitApplication
import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.install

@BukkitMain
class TriumphCompanion : BukkitApplication(
    start = TriumphApplication::commonStart,
) {

    override fun onStart() {
        install(Users) {
            consoleUser = BukkitConsoleUser()
        }

        install(Commands) {
            register(
                TestCommand()
            )
        }
    }
}