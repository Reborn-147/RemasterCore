pluginManagement {
    repositories {
        //Fabric
        maven("https://maven.fabricmc.net/")
        maven("https://maven.mymai1208.net/")
        //Legacy Fabric
        maven("https://repo.legacyfabric.net/repository/legacyfabric/")

        mavenCentral()
        gradlePluginPortal()
    }
}

buildscript {
    dependencies {
        classpath("com.google.code.gson:gson:2.10.1")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "ModTemplate147Kt"

