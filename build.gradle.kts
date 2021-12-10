import kotlin.math.sign

plugins {
    java
    `java-library`
    `maven-publish`
    signing
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

allprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
        plugin("signing")
        plugin("com.github.johnrengelman.shadow")
    }

    group = "com.gmail.furkanaxx34"

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val projectName = findProperty("projectname") as String? ?: "DLibrary"

    val sourcesJar by tasks.creating(Jar::class) {
        dependsOn("classes")
        archiveClassifier.convention("sources")
        archiveClassifier.set("sources")
        archiveBaseName.set(projectName)
        archiveVersion.set(null as String?)
        archiveVersion.convention(null as String?)
        from(sourceSets["main"].allSource)
    }

    tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        dependsOn(sourcesJar)
        archiveClassifier.set(null as String?)
        archiveVersion.set(null as String?)
        archiveVersion.convention(null as String?)
        archiveBaseName.set(projectName)
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
        mavenLocal()
    }
}

subprojects {
    dependencies {
        compileOnlyApi("org.projectlombok:lombok:1.18.22")
        compileOnlyApi("org.jetbrains:annotations:23.0.0")

        annotationProcessor("org.projectlombok:lombok:1.18.22")
        annotationProcessor("org.jetbrains:annotations:22.0.0")
    }
}

