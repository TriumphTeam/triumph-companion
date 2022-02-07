package dev.triumphteam.companion.user

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.identity.Identified
import java.util.UUID

interface CompanionUser : Audience, Identified {

    val uuid: UUID

}