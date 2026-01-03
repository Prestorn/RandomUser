plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.randomuser"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.randomuser"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":domain"))
    implementation(project(":data"))

    val koin_version = "4.1.1"
    val lifecycle_version = "2.9.4"
    val recyclerview_version = "1.4.0"
    val navigation_version = "2.9.5"
    val coroutines_version = "1.10.2"
    val fragment_version = "1.8.9"
    val retrofit_version = "3.0.0"
    val gson_version = "2.13.2"
    val material_version = "1.13.0"
    val glide_version = "5.0.5"
    val veiw_pager_version = "1.1.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.recyclerview:recyclerview:$recyclerview_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.google.code.gson:gson:$gson_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide_version")
    implementation("androidx.viewpager2:viewpager2:$veiw_pager_version")
}