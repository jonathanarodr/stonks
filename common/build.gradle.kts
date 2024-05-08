plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.common"
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.retrofit.core)
    implementation(libs.androidx.room.core)

    testImplementation(projects.testing)
    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
