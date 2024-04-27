package br.com.stonks.gradlebuild.plugins

import br.com.stonks.gradlebuild.conventions.configureClassFilter
import br.com.stonks.gradlebuild.conventions.configureCodeCoverage
import kotlinx.kover.gradle.plugin.dsl.KoverReportExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class CodeCoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")

            extensions.configure<KoverReportExtension> {
                configureCodeCoverage()
                configureClassFilter()
            }
        }
    }
}
