import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    `maven-publish`
}

allprojects {
    repositories {
        mavenLocal()
        maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.gradle.java-library")
    apply(plugin = "maven-publish")

    java.sourceCompatibility = JavaVersion.VERSION_11

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    publishing {
        repositories {
            maven {
                url = uri("https://artistry.airwallex.com/repository/lib-release/libs-release-local/")

                credentials {
                    username = System.getenv("ARTISTRY_USERNAME")
                    password = System.getenv("ARTISTRY_PASSWORD")
                }
            }
        }

        publications {
            val sourcesJar by tasks.registering(Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets.main.get().allSource)
            }

            create<MavenPublication>("default") {
                from(components["java"])
                artifact(sourcesJar.get())
            }
        }
    }
}
