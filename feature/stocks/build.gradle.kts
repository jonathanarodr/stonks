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

    implementation(libs.androidx.room.core)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.retrofit.core)
}
