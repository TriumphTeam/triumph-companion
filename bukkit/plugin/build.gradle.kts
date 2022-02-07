plugins {
    id("companion.base-conventions")
    id("me.mattstudios.triumph") version "0.2.7"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    // Spigot
    compileOnly(libs.spigot)

    // Modules
    implementation(project(":triumph-companion-common"))

    // TODO move this if needed
    api("net.kyori:adventure-text-serializer-legacy:4.9.3")

    //implementation(project(":paperweighttest", "reobf"))

    // Adventure
    implementation(libs.adventure.bukkit)

    // Core
    implementation(libs.core.bukkit)

    // Features
    implementation(libs.feature.bukkit.cmds)
    implementation(libs.feature.bukkit.listeners)
    implementation(libs.feature.config)
    implementation(libs.feature.locale)
}

bukkit {
    name = "TriumphCompanion"
    apiVersion = "1.18"
}