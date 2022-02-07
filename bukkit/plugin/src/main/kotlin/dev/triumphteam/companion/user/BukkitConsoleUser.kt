package dev.triumphteam.companion.user

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit

class BukkitConsoleUser : ConsoleUser {

    private val serializer = LegacyComponentSerializer.builder()
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build()

    private val console = Bukkit.getConsoleSender()

    override fun sendMessage(message: Component) {
        println("huh?")
        console.sendMessage(serializer.serialize(message))
    }
}