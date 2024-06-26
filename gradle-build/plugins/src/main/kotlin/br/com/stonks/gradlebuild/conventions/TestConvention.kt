package br.com.stonks.gradlebuild.conventions

import com.android.build.api.dsl.CommonExtension
import kotlinx.kover.gradle.plugin.dsl.KoverReportExtension
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.kotlin.dsl.retry

internal fun Test.configureUnitTestJvm() = apply {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

internal fun Project.configureUnitTest(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        testOptions {
            unitTests.apply {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
                all { test ->
                    test.testLogging {
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                }
            }
        }
    }
}

internal fun Project.configureAndroidTest(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
        }
        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            animationsDisabled = true
            unitTests.apply {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
                all { test ->
                    test.testLogging {
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                }
            }
        }
    }
}

@Suppress("MagicNumber")
internal fun KoverReportExtension.configureCodeCoverage() {
    defaults {
        xml {
            onCheck = false
        }
        html {
            onCheck = false
        }
        verify {
            onCheck = false
            rule {
                isEnabled = true
                minBound(20)
            }
        }
    }
}

internal fun KoverReportExtension.configureClassFilter() {
    filters {
        excludes {
            classes(
                "*.BuildConfig",
                "*.databinding.*Binding",
                "*.di.*ModuleKt*",
                "*_Impl*",
                "*DataBase*",
            )
            annotatedBy(
                "*Generated*",
                "androidx.room.Database",
                "androidx.compose.ui.tooling.preview.Preview",
            )
            packages()
        }
    }
}

@Suppress("MagicNumber")
internal fun Test.configureTestRetryStrategy() {
    retry {
        maxRetries.set(2)
        maxFailures.set(10)
        failOnPassedAfterRetry.set(true)
    }
}
