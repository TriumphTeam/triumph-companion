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

include("paperweighttest")

sequenceOf("api", "common").forEach(::includeProject)
sequenceOf("plugin").forEach(::includeBukkit)

fun includeProject(name: String) {
    include(name) {
        this.name = "${rootProject.name}-$name"
    }
}

fun includeBukkit(name: String) {
    include(name) {
        this.name = "${rootProject.name}-bukkit-$name"
        this.projectDir = file("bukkit/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}

enableFeaturePreview("VERSION_CATALOGS")