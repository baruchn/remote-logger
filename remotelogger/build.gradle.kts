import org.jetbrains.kotlin.gradle.tasks.*

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
        maven("https://dl.bintray.com/kotlin/kotlinx")
    }
}

val ideaActive = System.getProperty("idea.active") == "true"

val kotlin_version: String by project
val ktor_version: String by project
val coroutines_version: String by project
val serialization_version: String by project

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
    }
}

kotlin {
    val androidMain = android()

    val iosArm32 = iosArm32("iosArm32")
    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")

    if (ideaActive) {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }

        androidMain.project.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
            implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
            implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
            implementation("io.ktor:ktor-client-android:$ktor_version")
            implementation("io.ktor:ktor-client-okhttp:$ktor_version")
            implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
        }

        androidMain.project.kotlin.sourceSets.add(commonMain)

        val iosMain = if (ideaActive) {
            getByName("iosMain")
        } else {
            create("iosMain")
        }

        iosMain.apply {
            dependsOn(commonMain)

            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version")
                implementation("io.ktor:ktor-client-ios:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-native:$ktor_version")
            }
        }

        val iosArm32Main by getting
        val iosArm64Main by getting
        val iosX64Main by getting

        configure(listOf(iosArm32Main, iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
    }

    val frameworkName = "RemoteLogger"

    configure(listOf(iosArm32, iosArm64, iosX64)) {
        compilations {
            val main by getting {
                kotlinOptions.freeCompilerArgs += arrayOf("-Xobjc-generics")
            }
        }

        binaries.framework {
            export("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
            baseName = frameworkName
        }
    }

    tasks.register<FatFrameworkTask>("debugFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal (fat) debug framework"

        from(iosX64.binaries.getFramework("DEBUG"))
    }

    tasks.register<FatFrameworkTask>("releaseFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal (release) debug framework"

        from(iosArm64.binaries.getFramework("RELEASE"), iosArm32.binaries.getFramework("RELEASE"))
    }
}
