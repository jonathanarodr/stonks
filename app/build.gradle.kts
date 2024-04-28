plugins {
    alias(libs.plugins.stonks.android.application)
    alias(libs.plugins.stonks.android.application.compose)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}

dependencies {
    implementation(projects.infrastructure.network)
    implementation(projects.feature.home)

    implementation(libs.androidx.startup.runtime)

    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
