package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.bukkit.commands.commands
import dev.triumphteam.bukkit.listeners.listeners
import dev.triumphteam.companion.commands.admin.TestSpawn
import dev.triumphteam.companion.commands.tempSetupDefaults
import dev.triumphteam.companion.listeners.PlayerListener
import dev.triumphteam.core.BukkitApplication
import net.kyori.adventure.platform.bukkit.BukkitAudiences

@BukkitMain
class CompanionBukkit : BukkitApplication() {
    // To avoid doing `this@`.
    private val plugin = this

    lateinit var audiences: BukkitAudiences
        private set

    override fun onStart() {
        audiences = BukkitAudiences.create(this)

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