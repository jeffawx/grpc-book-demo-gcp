rootProject.name = "book-service"

pluginManagement {
    repositories {
        mavenLocal()
        maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
        gradlePluginPortal()
    }
}

include("book-api", "book-app")
