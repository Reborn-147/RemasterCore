plugins {
    id("fabric-loom") version "1.7.4"
    id("legacy-looming")version "1.7-SNAPSHOT" // Version must be the same as fabric-loom's
    kotlin("jvm") version "2.0.0"
    java
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://repo.legacyfabric.net/")
        maven("https://jitpack.io")
        maven("https://api.modrinth.com/maven") {
            content {
                includeGroup("maven.modrinth")
            }
        }
    }
    mavenLocal()
}

dependencies {
    val minecraft_version: String by project
    val yarn_mappings: String by project

    val loader_version: String by project
    val fabric_version: String by project

    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings("net.legacyfabric:yarn:${yarn_mappings}")
    modImplementation("net.fabricmc:fabric-loader:${loader_version}")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.11.0+kotlin.2.0.0")

    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("com.google.code.gson:gson:2.10")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"

    if (JavaVersion.current().isJava9Compatible) {
        options.release = 8
    }
}

kotlin {
    jvmToolchain(8)
}