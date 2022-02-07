plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin:0.16.1")
    implementation("io.papermc.paperweight.userdev:io.papermc.paperweight.userdev.gradle.plugin:1.3.4")
}