package dev.triumphteam.companion

import dev.triumphteam.companion.user.Users
import dev.triumphteam.core.TriumphApplication
import dev.triumphteam.core.feature.install

fun TriumphApplication.commonStart() {
    install(Users)
}