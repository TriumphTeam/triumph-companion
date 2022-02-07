package dev.triumphteam.companion.user

import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.ApplicationFeature
import dev.triumphteam.core.feature.attribute.key
import java.util.UUID

// TODO: 2/7/2022 Maybe load all players from storage instead of loading/unloading them on join/quit event
class Users(val consoleUser: CompanionUser) {

    private val users = mutableMapOf<UUID, CompanionUser>()

    fun getUser(uuid: UUID): CompanionUser {
        return users[uuid] ?: throw IllegalArgumentException("User with uuid $uuid not found.")
    }

    fun addUser(user: CompanionUser) {
        users[user.uuid] = user
    }

    fun exists(uuid: UUID): Boolean {
        return users.containsKey(uuid)
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