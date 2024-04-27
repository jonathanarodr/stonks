package br.com.stonks.gradlebuild.conventions

import br.com.stonks.gradlebuild.config.AndroidConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project

internal fun Project.configureAndroidApplication(
    extension: ApplicationExtension,
) {
    extension.apply {
        compileSdk = AndroidConfig.SDK_COMPILER

        defaultConfig {
            applicationId = AndroidConfig.APPLICATION_ID

            versionCode = AndroidConfig.VERSION_CODE
            versionName = AndroidConfig.VERSION_NAME

            minSdk = AndroidConfig.SDK_MINIMUM
            targetSdk = AndroidConfig.SDK_TARGET

            vectorDrawables.useSupportLibrary = true
        }

        packaging {
            resources {
                excludes.addAll(AndroidConfig.packagingExcludes)
            }
        }

        buildFeatures {
            buildConfig = true
            viewBinding = true
        }
    }
}

internal fun Project.configureAndroidLibrary(
    extension: LibraryExtension,
) {
    extension.apply {
        compileSdk = AndroidConfig.SDK_COMPILER

        defaultConfig {
            minSdk = AndroidConfig.SDK_MINIMUM
            vectorDrawables.useSupportLibrary = true
        }

        packaging {
            resources {
                excludes.addAll(AndroidConfig.packagingExcludes)
            }
        }

        buildFeatures {
            viewBinding = true
        }
    }
}

internal fun Project.configureSourceFiles(
    extension: AndroidComponentsExtension<*, *, *>,
) {
    extension.registerSourceType(AndroidConfig.JAVA_SOURCE_DIR_SHARED_TEST)
    extension.registerSourceType(AndroidConfig.KOTLIN_SOURCE_DIR_SHARED_TEST)
}
