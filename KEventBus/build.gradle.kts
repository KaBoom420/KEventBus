@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.ksp)

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

        withJava()
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
            implementation(libs.kotlinx.coroutines.core)
        }

        jvmMain.dependencies {
            implementation(libs.symbol.processing.api)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"])
            groupId = "com.github.KaBoom420"
            artifactId = "KEventBus"
            version = "1.0.0"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${project.findProperty("github.owner")}/${project.findProperty("github.repo")}")

            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")?.toString()
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")?.toString()
            }
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
