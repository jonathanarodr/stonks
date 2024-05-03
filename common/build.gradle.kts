plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.common"
}

dependencies {
    implementation(projects.testing)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.retrofit.core)

    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
