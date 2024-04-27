plugins {
    alias(libs.plugins.gradle.doctor) apply true
    alias(libs.plugins.gradle.dependency.analysis) apply true
    alias(libs.plugins.gradle.dependency.guard) apply false
    alias(libs.plugins.gradle.graph.assertion) apply false
    alias(libs.plugins.gradle.testretry) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlinx.kover) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
}

doctor {
    warnWhenJetifierEnabled.set(false)
}

dependencyAnalysis {
    structure {
        ignoreKtx(true)
    }
}
