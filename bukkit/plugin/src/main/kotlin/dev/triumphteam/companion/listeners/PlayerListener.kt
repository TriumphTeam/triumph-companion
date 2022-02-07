package dev.triumphteam.companion.listeners

import dev.triumphteam.companion.CompanionBukkit
import dev.triumphteam.companion.user.BukkitPlayerUser
import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.feature.get
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener(plugin: CompanionBukkit) : Listener {

    private val users = plugin[Users]
    private val audiences = plugin.audiences

    @EventHandler
    fun PlayerJoinEvent.onPlayerJoin() {
        val uuid = player.uniqueId
        if (users.exists(uuid)) return
        users.addUser(BukkitPlayerUser(audiences, uuid))
    }
}