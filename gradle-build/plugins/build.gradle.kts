import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "br.com.stonks.gradlebuild"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.gradle.testretry.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlinx.kover.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)

    implementation(libs.android.cachefix.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation.set(true)
        failOnWarning.set(true)
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "stonks.android.application"
            implementationClass = "br.com.stonks.gradlebuild.plugins.AndroidApplicationPlugin"
        }
        register("androidApplicationBuildType") {
            id = "stonks.android.application.build-type"
            implementationClass = "br.com.stonks.gradlebuild.plugins.AndroidApplicationBuildTypePlugin"
        }
        register("androidLibrary") {
            id = "stonks.android.library"
            implementationClass = "br.com.stonks.gradlebuild.plugins.AndroidLibraryPlugin"
        }
        register("androidApplicationCompose") {
            id = "stonks.android.application.compose"
            implementationClass = "br.com.stonks.gradlebuild.plugins.AndroidApplicationComposePlugin"
        }
        register("androidLibraryCompose") {
            id = "stonks.android.library.compose"
            implementationClass = "br.com.stonks.gradlebuild.plugins.AndroidLibraryComposePlugin"
        }
        register("kotlinLibrary") {
            id = "stonks.kotlin.library"
            implementationClass = "br.com.stonks.gradlebuild.plugins.KotlinLibraryPlugin"
        }
        register("codeStyle") {
            id = "stonks.codestyle"
            implementationClass = "br.com.stonks.gradlebuild.plugins.CodeStylePlugin"
        }
        register("codeCoverage") {
            id = "stonks.code-coverage"
            implementationClass = "br.com.stonks.gradlebuild.plugins.CodeCoveragePlugin"
        }
        register("telemetry") {
            id = "stonks.telemetry"
            implementationClass = "br.com.stonks.gradlebuild.plugins.TelemetryPlugin"
        }
    }
}
