package dev.triumphteam.companion.user

import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import java.util.UUID

class Users {

    private val users = mutableMapOf<UUID, CompanionUser>()

    fun getUser(uuid: UUID): CompanionUser {
        TODO("Not yet implemented")
    }

    companion object Feature : ApplicationFeature<TriumphApplication, Users, Users> {

        override val key = key<Users>("users")

        override fun install(application: TriumphApplication, configure: Users.() -> Unit): Users {
            return Users().apply(configure)
        }
    }
}