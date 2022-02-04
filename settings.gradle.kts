dependencyResolutionManagement {
    includeBuild("build-logic")
    repositories.gradlePluginPortal()
}

rootProject.name = "triumph-companion"

include("core")
include("plugin")

enableFeaturePreview("VERSION_CATALOGS")