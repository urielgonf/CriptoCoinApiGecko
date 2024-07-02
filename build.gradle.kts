// Top-level build file where you can add configuration options common to all sub-projects/modules.
/////////////////////firebase/////////////////
buildscript{

    repositories{
        google()
        mavenCentral()
    }
    dependencies{
        classpath ("com.google.gms:google-services:4.4.1")
    }

}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    ////////daggerhilt////////////////
    id("com.google.dagger.hilt.android") version "2.48" apply false


}