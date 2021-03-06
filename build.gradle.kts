// Top-level build file where you can add configuration options common to all sub-projects/modules.
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Build.buildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Document.dokkaGradleVersion)
    }
}

allprojects {
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}