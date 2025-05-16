import java.net.URI

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin="kotlin")
    apply(plugin="java")

    repositories {
        mavenCentral()
        maven {
            url = URI.create("https://maven.google.com/")
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}
