import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.airwallex.grpc-spring") version "1.1.2"
	id("com.google.cloud.tools.jib") version "3.1.4"
}

group = "com.airwallex.demo"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenLocal()
	maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
	mavenCentral()
}

dependencies {
	implementation("com.airwallex.demo:book-api:0.0.1")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly(platform("com.google.cloud:spring-cloud-gcp-dependencies:2.0.6"))
    runtimeOnly("com.google.cloud:spring-cloud-gcp-starter-trace")
    runtimeOnly("com.google.cloud:spring-cloud-gcp-starter-logging")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
    //from.image = "asia.gcr.io/airwallex/awx-openjdk11-jre-newrelic:1.11"
    to.image = "gcr.io/jfang-test/book-web"
}
