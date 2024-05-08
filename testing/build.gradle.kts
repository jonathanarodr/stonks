plugins {
    alias(libs.plugins.stonks.android.library)
}

android {
    namespace = "br.com.stonks.testing"

    configurations.all {
        val testGroup = projects.testing.group
        val testModule = projects.testing.name

        resolutionStrategy.eachDependency {
            if (requested.group == testGroup && requested.name == testModule) {
                if (project.hasProperty("release")) {
                    exclude(group = testGroup, module = testModule)
                }
            }
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.room.core)
    debugImplementation(libs.bundles.test.jvm)
    debugImplementation(libs.bundles.test.android)
}
