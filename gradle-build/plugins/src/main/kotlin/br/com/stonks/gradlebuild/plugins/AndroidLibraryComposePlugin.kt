package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.conventions.configureCompose
import br.com.stonks.gradlebuild.conventions.configureComposeDependencies
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidLibraryComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            extensions.configure<LibraryExtension> {
                configureCompose(this)
                configureComposeDependencies(this)
            }
        }
    }
}
