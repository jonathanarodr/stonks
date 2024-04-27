package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.apply
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class TelemetryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.autonomousapps.dependency-analysis",
                "com.dropbox.dependency-guard",
                "com.jraska.module.graph.assertion",
            )
        }
    }
}
