package dev.triumphteam.companion

import dev.triumphteam.annotations.BukkitMain
import dev.triumphteam.companion.commands.Commands
import dev.triumphteam.core.BukkitApplication
import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.install

@BukkitMain
class TriumphCompanion : BukkitApplication(
    start = TriumphApplication::commonStart,
) {

    override fun onStart() {
        install(Commands) {

        }
    }
}