package dev.triumphteam.companion.user

import net.kyori.adventure.audience.MessageType
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.Component
import java.util.UUID

class BukkitPlayerUser(private val audiences: BukkitAudiences, override val uuid: UUID) : PlayerUser {

    private val identity = Identity.identity(uuid)

    override fun identity(): Identity {
        //ForwardingAudience.Single
        return identity
    }

    override fun sendMessage(source: Identity, message: Component, type: MessageType) {
        audiences.player(uuid).sendMessage(source, message, type)
    }
}