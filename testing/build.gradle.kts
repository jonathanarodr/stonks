plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.testing"
}

dependencies {
    debugImplementation(libs.androidx.room.core)
    debugImplementation(libs.bundles.test.jvm)
    debugImplementation(libs.bundles.test.android)
}
