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
    implementation(project(Modules.MODULE_DOMAIN))
    implementation(project(Modules.MODULE_MODEL))
    implementation(project(Modules.SCREEN_DETAIL))
    implementation(project(Modules.MODULE_UTILS))

    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // LifeCycle
    implementation(LifeCycle.LIVEDATA_KTX)
    implementation(LifeCycle.VIEW_MODEL_KTX)

    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.DELEGATE)

    // Koin
    implementation(Koin.ANDROID)

    // Coroutines
    implementation(Coroutines.CORE)
    implementation(Coroutines.ANDROID)

    // Cicerone
    implementation(Cicerone.CICERONE)

    // Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}