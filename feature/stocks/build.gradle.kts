plugins {
    alias(libs.plugins.stonks.android.library)
    alias(libs.plugins.stonks.android.library.compose)
}

android {
    namespace = "br.com.stonks.feature.stocks"
}

dependencies {
    testImplementation(projects.testing)
    implementation(projects.common)
    implementation(projects.designSystem)
    implementation(projects.infrastructure.network)

    implementation(libs.retrofit.core)
    implementation(libs.androidx.room.core)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.bundles.test.jvm)
}
