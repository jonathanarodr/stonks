package br.com.stonks.gradlebuild.config

import org.gradle.api.JavaVersion

internal object AndroidConfig {

    const val APPLICATION_ID = "br.com.stonks"

    const val SDK_COMPILER = 34
    const val SDK_TARGET = 34
    const val SDK_MINIMUM = 23
    const val SDK_GMD_API = 30

    const val VERSION_MAJOR = "0"
    const val VERSION_MINOR = "0"
    const val VERSION_PATCH = "1"
    const val VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"
    const val VERSION_CODE = 1

    const val JAVA_SOURCE_DIR = "src/main/java"
    const val JAVA_SOURCE_DIR_TEST = "src/test/java"
    const val JAVA_SOURCE_DIR_SHARED_TEST = "src/sharedTest/java"
    const val JAVA_SOURCE_DIR_ANDROID_TEST = "src/androidTest/java"
    const val KOTLIN_SOURCE_DIR = "src/main/kotlin"
    const val KOTLIN_SOURCE_DIR_TEST = "src/test/kotlin"
    const val KOTLIN_SOURCE_DIR_SHARED_TEST = "src/sharedTest/kotlin"
    const val KOTLIN_SOURCE_DIR_ANDROID_TEST = "src/androidTest/kotlin"

    val javaVersion: JavaVersion = JavaVersion.VERSION_17

    val packagingExcludes: Set<String> = setOf(
        "META-INF/*.kotlin_module",
        "META-INF/LICENSE**",
        "META-INF/NOTICE",
        "META-INF/AL2.0",
        "META-INF/LGPL2.1",
        "DebugProbesKt.bin",
    )
}

fun JavaVersion.versionName(): String = this.toString()

fun JavaVersion.versionCode(): Int = this.versionName().toInt()
