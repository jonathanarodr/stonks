package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.config.AndroidConfig
import br.com.stonks.gradlebuild.config.versionName
import br.com.stonks.gradlebuild.detektPlugins
import br.com.stonks.gradlebuild.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class CodeStylePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<DetektExtension> {
                config.from(files("${project.rootDir}/tools/linters/detekt-rules.yml"))
                allRules = false
                parallel = true
                ignoredBuildTypes = listOf("release")
                source.from(
                    files(
                        AndroidConfig.KOTLIN_SOURCE_DIR,
                        AndroidConfig.KOTLIN_SOURCE_DIR_TEST,
                        AndroidConfig.KOTLIN_SOURCE_DIR_SHARED_TEST,
                        AndroidConfig.KOTLIN_SOURCE_DIR_ANDROID_TEST,
                        AndroidConfig.JAVA_SOURCE_DIR,
                        AndroidConfig.JAVA_SOURCE_DIR_TEST,
                        AndroidConfig.JAVA_SOURCE_DIR_SHARED_TEST,
                        AndroidConfig.JAVA_SOURCE_DIR_ANDROID_TEST,
                    )
                )
            }

            tasks.withType<Detekt>().configureEach {
                jvmTarget = AndroidConfig.javaVersion.versionName()
            }

            tasks.withType<DetektCreateBaselineTask>().configureEach {
                jvmTarget = AndroidConfig.javaVersion.versionName()
            }

            disableDetektOnCheck()

            dependencies {
                detektPlugins(libs.findLibrary("detekt-formatting").get())
                detektPlugins(libs.findLibrary("detekt-compose").get())
            }
        }
    }

    private fun Project.disableDetektOnCheck() {
        tasks.named("check").configure {
            this.setDependsOn(
                this.dependsOn.filterNot {
                    it is TaskProvider<*> && it.name == "detekt"
                }
            )
        }
    }
}
