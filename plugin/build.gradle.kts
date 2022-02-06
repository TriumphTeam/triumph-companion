plugins {
    id("companion.base-conventions")
    id("me.mattstudios.triumph") version "0.2.7"
}

repositories {
    maven("https://repo.triumphteam.dev/releases/")
    maven("https://repo.triumphteam.dev/snapshots/")
}

dependencies {
    compileOnly(libs.spigot)

    implementation(libs.core.bukkit)
    implementation(libs.feature.bukkit.cmds)
    implementation(libs.feature.config)
    implementation(libs.feature.locale)
}