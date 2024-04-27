package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.conventions.configureCompose
import br.com.stonks.gradlebuild.conventions.configureComposeDependencies
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidApplicationComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                configureCompose(this)
                configureComposeDependencies(this)
            }
        }
    }
}
