plugins {
    alias(libs.plugins.stonks.android.application)
    alias(libs.plugins.stonks.android.application.compose)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}

dependencies {
    implementation(projects.common)
    implementation(projects.designSystem)
    implementation(projects.infrastructure.network)
    implementation(projects.feature.home)
    implementation(projects.feature.stocks)

    implementation(libs.androidx.startup.runtime)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
