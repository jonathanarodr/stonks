plugins {
    alias(libs.plugins.stonks.android.library)
    alias(libs.plugins.stonks.android.library.compose)
}

android {
    namespace = "br.com.stonks.feature.home"
}

dependencies {
    testImplementation(projects.testing)
    implementation(projects.common)
    implementation(projects.designSystem)
    implementation(projects.infrastructure.network)

    implementation(libs.retrofit.core)

    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
