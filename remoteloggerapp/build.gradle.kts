plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
}

val kotlin_version: String by project
val ktor_version: String by project
val coroutines_version: String by project
val serialization_version: String by project

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        encoding = "UTF-8"
        setSourceCompatibility("1.8")
        setTargetCompatibility("1.8")
    }
    packagingOptions { exclude("META-INF/*.kotlin_module") }
}

//android {
//    compileSdkVersion 29
//
//    defaultConfig {
//        applicationId "il.co.napps.remoteloggerapp"
//        minSdkVersion 21
//        targetSdkVersion 29
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//
//}

dependencies {
    implementation(project.fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0")
    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0")
//    testImplementation 'junit:junit:4.12'
//    androidTestImplementation "androidx.test:runner:1.2.0"
//    androidTestImplementation "androidx.test.espresso:espresso-core:3.2.0"

    implementation(project(":remotelogger"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")

}
