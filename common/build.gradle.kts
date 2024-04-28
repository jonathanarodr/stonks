plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.common"
}

dependencies {
    implementation(libs.gson)
}
