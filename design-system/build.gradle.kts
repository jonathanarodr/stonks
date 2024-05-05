plugins {
    alias(libs.plugins.stonks.android.library)
    alias(libs.plugins.stonks.android.library.compose)
}

android {
    namespace = "br.com.stonks.designsystem"
}

dependencies {
    implementation(projects.common)

    testImplementation(libs.bundles.test.jvm)
}
