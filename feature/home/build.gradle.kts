plugins {
    alias(libs.plugins.stonks.android.library)
    alias(libs.plugins.stonks.android.library.compose)
}

android {
    namespace = "br.com.stonks.feature.home"
}

dependencies {
    implementation(projects.infrastructure.network)

    implementation(libs.gson)
    implementation(libs.retrofit.core)
}
