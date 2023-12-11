import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.myfood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myfood"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val props = Properties()
    props.load(FileInputStream("environment.properties"))
    buildTypes {

        debug {
            buildConfigField("String","BASE_URL",props["BASE_URL_DEV"].toString())
        }
        release {
            buildConfigField("String","BASE_URL",props["BASE_URL_PROD"].toString())
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

    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    val navVersion = "2.7.5"
    val retrofitVersion = "2.9.0"
    val glideVersion = "4.16.0"
    val lifecycleVersion = "2.6.2"
    val work_version = "2.9.0"


    //Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //gif
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    //ksp
    ksp("androidx.room:room-compiler:2.6.1")

    //worker
    implementation("androidx.work:work-runtime-ktx:$work_version")

    // Default dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}