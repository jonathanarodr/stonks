plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.infrastructure.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "SERVER_URL", "\"https://api.github.com\"")
        buildConfigField("String", "SERVER_PATH", "\"/repos/jonathanarodr/stonks/contents/mocks\"")
        buildConfigField("String", "AUTHORIZATION_KEY", "\"ghp_{YOUR_API_KEY}\"")
    }
}

dependencies {
    testImplementation(projects.testing)
    implementation(projects.common)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)

    testImplementation(libs.bundles.test.jvm)
}
