plugins {
    id("com.airwallex.grpc-spring") version "1.1.2"
    id("com.google.cloud.tools.jib") version "3.1.4"
}

dependencies {
    implementation(project(":book-api"))
    runtimeOnly("org.springframework.boot:spring-boot-starter-webflux")
}

jib {
    //from.image = "asia.gcr.io/airwallex/awx-openjdk11-jre-newrelic:1.11"
    to.image = "gcr.io/jfang-test/book-service"
}
