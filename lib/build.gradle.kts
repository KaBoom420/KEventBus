@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}

val androidMinSdk     : String by project
val androidCompileSdk : String by project
val libNamespace      : String by project
val libGroup          : String by project
val libVersion        : String by project

group   = libGroup
version = libVersion

kotlin {
    jvm()
    androidLibrary {
        namespace  = libNamespace
        compileSdk = androidCompileSdk.toInt()
        minSdk     = androidMinSdk.toInt()
        version    = libVersion

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name.set("KEventBus")
        description.set("A lightweight, coroutine-based Multiplatform Event Bus with TTL, delayed delivery, logging and KSP-generated subscribers.")
        inceptionYear.set("2025")
        url.set("https://github.com/KaBoom420/KEventBus")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("kaboom")
                name.set("KaBoom")
                url.set("https://github.com/KaBoom420")
            }
        }

        scm {
            url.set("https://github.com/KaBoom420/KEventBus")
            connection.set("scm:git:https://github.com/KaBoom420/KEventBus.git")
            developerConnection.set("scm:git:ssh://git@github.com/KaBoom420/KEventBus.git")
        }
    }

}
