plugins {
    id("companion.base-conventions")
}

dependencies {
    api(project(":triumph-companion-api"))
    api(libs.core)
    api(libs.commands)
}