import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    id("com.github.hierynomus.license")
    kotlin("jvm")
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
            jvmTarget = "1.8"
            javaParameters = true
        }
    }
}