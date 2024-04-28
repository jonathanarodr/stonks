package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.apply
import br.com.stonks.gradlebuild.config.AndroidConfig
import br.com.stonks.gradlebuild.conventions.configureAndroidApplication
import br.com.stonks.gradlebuild.conventions.configureAndroidTest
import br.com.stonks.gradlebuild.conventions.configureDeviceManager
import br.com.stonks.gradlebuild.conventions.configureKotlinAndroid
import br.com.stonks.gradlebuild.conventions.configureSourceFiles
import br.com.stonks.gradlebuild.conventions.configureTestRetryStrategy
import br.com.stonks.gradlebuild.conventions.configureUnitTest
import br.com.stonks.gradlebuild.implementation
import br.com.stonks.gradlebuild.libs
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.application",
                "org.gradle.android.cache-fix",
                "org.gradle.test-retry",
                "org.jetbrains.kotlin.android",
                "kotlin-parcelize",
                "com.google.devtools.ksp",
                "stonks.android.application.build-type",
                "stonks.codestyle",
                "stonks.code-coverage",
                "stonks.telemetry",
            )

            extensions.configure<ApplicationExtension> {
                configureAndroidApplication(this)
                configureKotlinAndroid(this)
                configureUnitTest(this)
                configureAndroidTest(this)
                configureDeviceManager(this)

                namespace = AndroidConfig.APPLICATION_ID
                testNamespace = "${AndroidConfig.APPLICATION_ID}.test"

                dependenciesInfo {
                    includeInApk = true
                    includeInBundle = true
                }

                testFixtures {
                    enable = true
                }
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureSourceFiles(this)
            }

            tasks.withType<Test>().configureEach {
                configureTestRetryStrategy()
            }

            dependencies {
                implementation(libs.findLibrary("timber").get())

                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-android").get())

                implementation(libs.findLibrary("gson").get())
            }
        }
    }
}
