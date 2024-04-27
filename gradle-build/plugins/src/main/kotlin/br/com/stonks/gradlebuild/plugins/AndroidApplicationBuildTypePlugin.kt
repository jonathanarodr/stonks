package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.config.AndroidBuildType
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidApplicationBuildTypePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureBuildTypes(this)
            }
        }
    }

    private fun Project.configureBuildTypes(extension: ApplicationExtension) {
        extension.buildTypes {
            getByName(AndroidBuildType.DEBUG.identifier) {
                isDebuggable = true
                enableUnitTestCoverage = true
                isMinifyEnabled = false
                isShrinkResources = false
                applicationIdSuffix = ".debug"
                versionNameSuffix = "-alpha"
            }

            getByName(AndroidBuildType.RELEASE.identifier) {
                isDebuggable = false
                enableUnitTestCoverage = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    extension.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}
