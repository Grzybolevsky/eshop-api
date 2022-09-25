import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion: String by project

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("nl.littlerobots.version-catalog-update") version "0.6.1"
    id("com.diffplug.spotless") version "6.11.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC1"
    id("com.google.cloud.tools.jib") version "3.3.0"
    kotlin("jvm") version "1.7.20-RC"
    kotlin("plugin.spring") version "1.7.20-RC"
    kotlin("plugin.jpa") version "1.7.20-RC"
    kotlin("plugin.allopen") version "1.7.20-RC"
    kotlin("plugin.serialization") version "1.7.20-RC"
}

group = "com.grzybolevsky"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val snippetsDir by extra { file("build/generated-snippets") }

dependencies {
    implementation(libs.postgres)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.spring) { exclude(group = "org.apache.logging.log4j") }

    testImplementation(libs.h2)
    testImplementation(libs.bundles.test) {
        exclude(module = "junit")
        exclude(group = "org.mockito")
        exclude(group = "org.apache.logging.log4j")
    }

    developmentOnly(libs.spring.devtools) { exclude(group = "org.apache.logging.log4j") }
    annotationProcessor(libs.spring.processor)
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.17.3")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.4")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = javaVersion
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(snippetsDir)
}

jib {
    from {
        image = "eclipse-temurin:18.0.2.1_1-jre-jammy"
    }
    container {
        mainClass = "com.grzybolevsky.eshop.ApplicationKt"
        ports = listOf("8080")
    }
}

spotless {
    kotlin {
        ktlint()
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}