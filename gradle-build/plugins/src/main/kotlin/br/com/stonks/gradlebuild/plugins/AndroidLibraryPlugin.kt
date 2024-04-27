package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.apply
import br.com.stonks.gradlebuild.conventions.configureAndroidLibrary
import br.com.stonks.gradlebuild.conventions.configureAndroidTest
import br.com.stonks.gradlebuild.conventions.configureDeviceManager
import br.com.stonks.gradlebuild.conventions.configureKotlinAndroid
import br.com.stonks.gradlebuild.conventions.configureSourceFiles
import br.com.stonks.gradlebuild.conventions.configureTestRetryStrategy
import br.com.stonks.gradlebuild.conventions.configureUnitTest
import br.com.stonks.gradlebuild.implementation
import br.com.stonks.gradlebuild.libs
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.library",
                "org.gradle.android.cache-fix",
                "org.gradle.test-retry",
                "org.jetbrains.kotlin.android",
                "kotlin-parcelize",
                "com.google.devtools.ksp",
                "stonks.codestyle",
                "stonks.code-coverage",
            )

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureKotlinAndroid(this)
                configureUnitTest(this)
                configureAndroidTest(this)
                configureDeviceManager(this)

                testFixtures {
                    enable = true
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                configureSourceFiles(this)
            }

            tasks.withType<Test>().configureEach {
                configureTestRetryStrategy()
            }

            dependencies {
                implementation(libs.findLibrary("timber").get())

                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-android").get())

                implementation(libs.findLibrary("kotlinx-serialization-json").get())
                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                implementation(libs.findLibrary("kotlinx-coroutines-android").get())
                implementation(libs.findLibrary("kotlinx-coroutines-playservices").get())

                implementation(libs.findLibrary("androidx-core").get())
                implementation(libs.findLibrary("androidx-appcompat").get())

                implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                implementation(libs.findLibrary("androidx-lifecycle-livedata").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime").get())
            }
        }
    }
}
