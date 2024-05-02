package br.com.stonks.gradlebuild.conventions

import br.com.stonks.gradlebuild.androidTestImplementation
import br.com.stonks.gradlebuild.debugImplementation
import br.com.stonks.gradlebuild.implementation
import br.com.stonks.gradlebuild.libs
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidx-compose-compiler").get().toString()
        }
    }
}

fun Project.configureComposeDependencies(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
            implementation(libs.findLibrary("androidx-compose-runtime").get())
            implementation(libs.findLibrary("androidx-compose-runtime-livedata").get())
            implementation(libs.findLibrary("androidx-compose-runtime-tracing").get())
            implementation(libs.findLibrary("androidx-compose-ui").get())
            implementation(libs.findLibrary("androidx-compose-foundation").get())
            implementation(libs.findLibrary("androidx-compose-material").get())
            implementation(libs.findLibrary("androidx-compose-material3").get())
            implementation(libs.findLibrary("androidx-compose-graphics").get())
            implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
            implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
            implementation(libs.findLibrary("androidx-activity-compose").get())
            implementation(libs.findLibrary("androidx-navigation-compose").get())
            implementation(libs.findLibrary("androidx-navigation-runtime").get())
            implementation(libs.findLibrary("coil-compose").get())

            implementation(platform(libs.findLibrary("koin-bom").get()))
            implementation(libs.findLibrary("koin-compose").get())

            debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
            debugImplementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

            androidTestImplementation(platform(libs.findLibrary("androidx-compose-bom").get()))
        }
    }
}
