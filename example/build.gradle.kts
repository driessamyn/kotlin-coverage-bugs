plugins {
    kotlin("jvm") version "2.1.21"
    `java-library`
//    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    id("jacoco")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.13.3")
    testImplementation("org.junit.platform:junit-platform-engine:1.10.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.10.2")
    testImplementation("io.mockk:mockk:1.14.5")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

//kover {
//    useJacoco("0.8.13")
//}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
    }
}