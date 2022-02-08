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

sequenceOf("api", "plugin").forEach(::includeProject)
sequenceOf("eighteen").forEach(::includeVersion)

fun includeProject(name: String) {
    include(name) {
        this.name = "${rootProject.name}-$name"
    }
}

fun includeVersion(name: String) {
    include(name) {
        this.name = "${rootProject.name}-$name"
        this.projectDir = file("versions/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}

enableFeaturePreview("VERSION_CATALOGS")