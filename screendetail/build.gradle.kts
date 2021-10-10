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

    buildFeatures {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.MODULE_MODEL))
    implementation(project(Modules.MODULE_DOMAIN))
    implementation(project(Modules.MODULE_UTILS))

    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.CONSTRAINT_LAYOUT)

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.DELEGATE)

    // Cicerone
    implementation(Cicerone.CICERONE)

    // Glide
    annotationProcessor(Glide.COMPILER)
    implementation(Glide.GLIDE)
    implementation(Glide.GLIDE_OKHTTP3)
    kapt(Glide.COMPILER)

    // Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}