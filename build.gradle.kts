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
    version = findProperty("projectVersion") as String? ?: "1.0.0";

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

    val javadocJar by tasks.creating(Jar::class) {
        dependsOn("javadoc")
        archiveClassifier.convention("")
        archiveClassifier.set("javadoc")
        archiveBaseName.set(projectName)
        archiveVersion.set(null as String?)
        archiveVersion.convention(null as String?)
        from(tasks.javadoc)
    }

    tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        dependsOn(sourcesJar)
        dependsOn(javadocJar)
        archiveClassifier.set(null as String?)
        archiveVersion.set(null as String?)
        archiveVersion.convention(null as String?)
        archiveBaseName.set(projectName)
        relocate("com.cryptomorin.xseries", "com.gmail.furkanaxx34.dlibrary.xseries")
        relocate("io.github.bananapuncher714.nbteditor", "com.gmail.furkanaxx34.dlibrary.nbteditor")
        relocate("net.wesjd.anvilgui", "com.gmail.furkanaxx34.dlibrary.anvilgui")
        relocate("org.yaml.snakeyaml", "com.gmail.furkanaxx34.dlibrary.snakeyaml")
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }

    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
        (options as StandardJavadocDocletOptions).tags("todo")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                groupId = project.group.toString()
                artifactId = projectName
                version = project.version.toString()

                project.shadow.component(this)
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
                pom {
                    name.set("Dantero Plugin Library")
                    description.set("Dantero plugin library.")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://mit-license.org/license.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("Dantero")
                            name.set("Furkan Doğan")
                            email.set("furkanaxx34@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/FurkanDGN/PluginLibrary.git")
                        developerConnection.set("scm:git:ssh://github.com/FurkanDGN/PluginLibrary.git")
                        url.set("https://github.com/FurkanDGN/PluginLibrary")
                    }
                }
            }
        }
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
        mavenLocal()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://repo.codemc.org/repository/maven-snapshots")
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

