package dev.triumphteam.companion.features

import dev.triumphteam.companion.TriumphCompanion
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import net.kyori.adventure.platform.bukkit.BukkitAudiences

object Audiences : ApplicationFeature<TriumphCompanion, BukkitAudiences, BukkitAudiences> {

    override val key = key<BukkitAudiences>("audiences")

    override fun install(application: TriumphCompanion, configure: BukkitAudiences.() -> Unit): BukkitAudiences {
        return BukkitAudiences.create(application)
    }
}