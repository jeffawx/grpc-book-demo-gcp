plugins {
    id("com.airwallex.grpc-spring") version "1.2.4"
    id("com.google.cloud.tools.jib") version "3.1.4"
}

dependencies {
    implementation(project(":book-api"))
    runtimeOnly("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly(platform("com.google.cloud:spring-cloud-gcp-dependencies:2.0.7"))
    runtimeOnly("com.google.cloud:spring-cloud-gcp-starter-trace")
    runtimeOnly("com.google.cloud:spring-cloud-gcp-starter-logging")
}

jib {
    to.image = "gcr.io/jfang-test/book-service"
}
