import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
}

repositories {
    mavenCentral()
    mavenLocal()
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

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        //options.compilerArgs.add("-Xlint:unchecked")
    }
}