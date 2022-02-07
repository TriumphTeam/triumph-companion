package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.bukkit.listeners.listeners
import dev.triumphteam.companion.commands.Commands
import dev.triumphteam.companion.commands.TestCommand
import dev.triumphteam.companion.listeners.PlayerListener
import dev.triumphteam.companion.user.BukkitConsoleUser
import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.BukkitApplication
import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.install
import net.kyori.adventure.platform.bukkit.BukkitAudiences

@BukkitMain
class CompanionBukkit : BukkitApplication(
    start = TriumphApplication::commonStart,
) {
    // To avoid doing `this@`.
    private val plugin = this

    lateinit var audiences: BukkitAudiences
        private set

    override fun onStart() {
        audiences = BukkitAudiences.create(this)

        install(Users) {
            consoleUser = BukkitConsoleUser()
        }

        install(Commands) {
            register(
                TestCommand()
            )
        }

        listeners {
            register(
                PlayerListener(plugin)
            )
        }
    }
}