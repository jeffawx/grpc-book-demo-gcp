plugins {
    id("com.airwallex.grpc-spring") version "1.1.2"
}

dependencies {
    implementation(project(":book-api"))
    runtimeOnly("org.springframework.boot:spring-boot-starter-webflux")
}
