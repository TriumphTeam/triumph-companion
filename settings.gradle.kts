dependencyResolutionManagement {
    includeBuild("build-logic")
    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "triumph-companion"

includeProject("api")
includeProject("bukkit", "plugin")

sequenceOf("eighteen").forEach(::includeBukkitVersion)

fun includeProject(name: String) {
    include(name) {
        this.name = "${rootProject.name}-$name"
    }
}

fun includeProject(folder: String, name: String) {
    include(name) {
        this.name = "${rootProject.name}-$name"
        this.projectDir = file("$folder/$name")
    }
}

fun includeBukkitVersion(name: String) {
    include(name) {
        this.name = "${rootProject.name}-bukkit-$name"
        this.projectDir = file("bukkit/versions/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}

enableFeaturePreview("VERSION_CATALOGS")