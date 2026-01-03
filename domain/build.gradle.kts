plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    val coroutines_version = "1.10.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
}