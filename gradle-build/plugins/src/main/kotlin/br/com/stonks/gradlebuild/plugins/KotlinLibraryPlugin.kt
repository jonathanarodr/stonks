package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.apply
import br.com.stonks.gradlebuild.conventions.configureKotlinJvm
import br.com.stonks.gradlebuild.conventions.configureTestRetryStrategy
import br.com.stonks.gradlebuild.conventions.configureUnitTestJvm
import br.com.stonks.gradlebuild.implementation
import br.com.stonks.gradlebuild.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KotlinLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "org.jetbrains.kotlin.jvm",
                "org.gradle.test-retry",
                "stonks.codestyle",
                "stonks.code-coverage",
            )

            configureKotlinJvm()

            tasks.withType<Test>().configureEach {
                configureTestRetryStrategy()
                configureUnitTestJvm()
            }

            dependencies {
                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-core").get())

                implementation(libs.findLibrary("gson").get())

                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                implementation(libs.findLibrary("kotlinx-collections-immutable").get())
            }
        }
    }
}
