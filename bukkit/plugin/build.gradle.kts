plugins {
    id("companion.base-conventions")
    id("me.mattstudios.triumph") version "0.2.7"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    compileOnly(libs.spigot)

    implementation(project(":triumph-companion-common"))

    //implementation(project(":paperweighttest", "reobf"))

    implementation(libs.core.bukkit)
    implementation(libs.feature.bukkit.cmds)
    implementation(libs.feature.config)
    implementation(libs.feature.locale)
}

bukkit {
    name = "TriumphCompanion"
}