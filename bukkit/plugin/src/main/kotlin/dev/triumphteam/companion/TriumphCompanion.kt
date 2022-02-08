package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.bukkit.commands.commands
import dev.triumphteam.bukkit.listeners.listeners
import dev.triumphteam.companion.commands.admin.TestSpawn
import dev.triumphteam.companion.commands.tempSetupDefaults
import dev.triumphteam.companion.features.Audiences
import dev.triumphteam.companion.features.Controller
import dev.triumphteam.companion.listeners.PlayerListener
import dev.triumphteam.core.BukkitApplication
import dev.triumphteam.core.feature.install

@BukkitMain
class TriumphCompanion : BukkitApplication() {
    // To avoid doing `this@`.
    private val plugin = this

    override fun onStart() {
        install(Controller)
        install(Audiences)

        commands {
            tempSetupDefaults()

            register(
                TestSpawn()
            )
        }

        listeners {
            register(
                PlayerListener(plugin)
            )
        }
    }
}

