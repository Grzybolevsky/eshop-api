import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion: String by project

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("nl.littlerobots.version-catalog-update") version "0.6.1"
    id("com.diffplug.spotless") version "6.11.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC1"
    id("com.google.cloud.tools.jib") version "3.3.0"
    id("com.google.devtools.ksp") version "1.7.20-RC-1.0.6"
    id("com.bnorm.power.kotlin-power-assert") version "0.12.0"
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
    implementation(libs.arrow.core)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.spring)
    implementation(libs.bundles.util)
    implementation(libs.postgres)
    implementation(libs.springdoc)
    ksp(libs.arrow.optics)

    testImplementation(libs.bundles.test) {
        exclude(module = "junit")
        exclude(group = "org.mockito")
    }
    testImplementation(libs.h2)

    annotationProcessor(libs.spring.processor)
    developmentOnly(libs.spring.devtools)
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.17.3")
        mavenBom("com.azure.spring:spring-cloud-azure-dependencies:4.3.0")
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

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = javaVersion
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = javaVersion
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

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}