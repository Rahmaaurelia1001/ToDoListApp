plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.todolistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolistapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.material3)
        implementation("androidx.compose.ui:ui:1.5.1")
        implementation("androidx.compose.ui:ui-tooling:1.5.1")
        implementation("androidx.navigation:navigation-compose:2.6.0")
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
        implementation ("androidx.compose.material3:material3:1.0.0")
        implementation ("androidx.compose.material3:material3-window-size-class:1.0.0")
        implementation ("androidx.compose.ui:ui:1.4.0")
        implementation ("androidx.compose.material3:material3:1.0.0")
        implementation ("androidx.compose.material3:material3-window-size-class:1.0.0")
        implementation ("androidx.navigation:navigation-compose:2.7.0")
        implementation ("androidx.compose.ui:ui:1.0.0")
    }



}