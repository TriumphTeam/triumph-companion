package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.bukkit.commands.Commands
import dev.triumphteam.core.BukkitPlugin
import dev.triumphteam.core.feature.install

@BukkitMain
class TriumphCompanion : BukkitPlugin() {

    override fun onEnable() {
        install(Commands)
    }

}