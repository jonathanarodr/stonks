plugins {
    alias(libs.plugins.stonks.android.application)
    alias(libs.plugins.stonks.android.application.compose)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}

dependencies {
    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
