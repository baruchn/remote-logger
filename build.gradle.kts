buildscript {
    extra["kotlin_version"] = "1.3.61"
    extra["ktor_version"] = "1.3.0"
    extra["coroutines_version"] = "1.3.3"
    extra["serialization_version"] = "0.14.0"

    val kotlin_version = "1.3.61"
    val gradle_android_version = "4.0.0-alpha09"

    repositories {
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")

        google()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:${gradle_android_version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlinx")
        maven("https://dl.bintray.com/kotlin/ktor")
        maven("https://dl.bintray.com/sargunster/maven")
        maven("https://dl.bintray.com/kotlin/squash")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")

        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
