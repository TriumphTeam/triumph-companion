package dev.triumphteam.companion.user

import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import java.util.UUID

class Users(val consoleUser: CompanionUser) {

    private val users = mutableMapOf<UUID, CompanionUser>()

    fun getUser(uuid: UUID): CompanionUser {
        TODO("Not yet implemented")
    }

    class UsersConfiguration {
        lateinit var consoleUser: ConsoleUser
    }

    companion object Feature : ApplicationFeature<TriumphApplication, UsersConfiguration, Users> {

        override val key = key<Users>("users")

        override fun install(application: TriumphApplication, configure: UsersConfiguration.() -> Unit): Users {
            val consoleUser = UsersConfiguration().apply(configure).consoleUser
            return Users(consoleUser)
        }
    }
}