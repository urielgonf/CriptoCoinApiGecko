plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    ///////injeccion de dependencias///////
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    ////////firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.myportfolio.portfoliocritocoinapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myportfolio.portfoliocritocoinapplication"
        minSdk = 24
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
        //////////////
        getByName("debug"){
            applicationIdSuffix =".debug"
            isDebuggable = true
        }
       ///////////////////////////////////


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

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    ////lifedat

    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")

    //////icons
    implementation("androidx.compose.material:material-icons-extended:1.6.7")
    ///////navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation( "com.google.accompanist:accompanist-navigation-animation:0.18.0")


    ////firebase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    ////retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    ///images
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("io.coil-kt:coil-gif:2.1.0")
    ///animationcards
    implementation("com.google.accompanist:accompanist-pager:0.20.0")
    implementation("androidx.compose.ui:ui-util:1.6.7")
    implementation("androidx.compose.animation:animation-core:1.6.7")
    //implementation("com.google.accompanist:accompanist-text:0.21.0")

    ////pagination
    implementation("androidx.paging:paging-runtime:3.3.0")
    implementation("androidx.paging:paging-compose:3.3.0")
    /////////////////////////
    //animations
    implementation( "androidx.compose.animation:animation:1.6.7")

    /////Dagger Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-common:1.2.0")
    ///Room
    implementation("androidx.room:room-runtime-android:2.7.0-alpha03")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    ///workmanager
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    implementation ("androidx.work:work-runtime-ktx:2.9.0")
    implementation ("androidx.work:work-runtime:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.work:work-multiprocess:2.9.0")

}
