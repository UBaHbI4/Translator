plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
    }
}

dependencies {

    implementation(project(Modules.MODULE_MODEL))
    implementation(project(Modules.MODULE_DOMAIN))

    // Design
    implementation(Design.MATERIAL)

    // Kotlin
    implementation(Kotlin.CORE)

    // Rx-Java
    implementation(RxJava.ANDROID)

    // Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}