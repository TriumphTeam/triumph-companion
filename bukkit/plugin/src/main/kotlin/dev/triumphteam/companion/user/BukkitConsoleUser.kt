package dev.triumphteam.companion.user

import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import java.util.UUID

class BukkitConsoleUser : ConsoleUser {

    // TODO: 2/7/2022 Idk what to do for the UUID on the console yet
    override val uuid: UUID = UUID.randomUUID()

    private val serializer = LegacyComponentSerializer.builder()
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build()

    private val console = Bukkit.getConsoleSender()

    override fun identity(): Identity {
        return Identity.nil()
    }

    override fun sendMessage(message: Component) {
        console.sendMessage(serializer.serialize(message))
    }
}