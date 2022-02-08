package dev.triumphteam.companion.features

import dev.triumphteam.companion.TriumphCompanion
import dev.triumphteam.companion.eighteen.EighteenEntityController
import dev.triumphteam.companion.entity.CompanionController
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import org.bukkit.entity.Entity

object Controller : ApplicationFeature<TriumphCompanion, CompanionController<Entity>, CompanionController<Entity>> {

    override val key = key<CompanionController<Entity>>("controller")

    override fun install(
        application: TriumphCompanion,
        configure: CompanionController<Entity>.() -> Unit
    ): CompanionController<Entity> {
        // TODO: 2/8/2022 Add version check here
        return EighteenEntityController()
    }
}