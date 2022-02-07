package dev.triumphteam.companion.commands.admin

import dev.triumphteam.cmd.core.annotation.SubCommand
import dev.triumphteam.companion.commands.BaseCompanionCommand
import org.bukkit.entity.Player

class TestSpawn : BaseCompanionCommand() {

    @SubCommand("spawn")
    fun Player.spawn() {
        sendMessage("testing")
    }
}