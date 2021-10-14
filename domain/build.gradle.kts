import java.io.FileInputStream
import java.util.*

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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes.forEach {
        val properties = Properties()
        properties.load(FileInputStream(file( "./../api.properties")))
        val appToken = properties.getProperty("token", "")
        it.buildConfigField("String", "API_TOKEN", appToken)
        val urlBase = properties.getProperty("base_url", "")
        it.buildConfigField("String", "BASE_URL", urlBase)
    }
}

dependencies {

    implementation(project(Modules.MODULE_MODEL))

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // Retrofit 2
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    // Room
    implementation(Room.KTX)
    kapt(Room.COMPILER)

    // Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}