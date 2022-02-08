import gradle.kotlin.dsl.accessors._cf989acd7c53d601699eb2d609b231d6.java
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    id("com.github.hierynomus.license")
    kotlin("jvm")
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.triumphteam.dev/releases/")
    maven("https://repo.triumphteam.dev/snapshots/")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).apply {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

license {
    header = rootProject.file("LICENSE")
    encoding = "UTF-8"
    mapping("kotlin", "JAVADOC_STYLE")
    mapping("java", "JAVADOC_STYLE")

    include("**/*.kt")
    include("**/*.java")
}

repositories {
    mavenCentral()
    mavenLocal()
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        //options.compilerArgs.add("-Xlint:unchecked")
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }
}