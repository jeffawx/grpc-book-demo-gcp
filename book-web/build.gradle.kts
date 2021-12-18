import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.airwallex.grpc-spring") version "1.1.2"
}

group = "com.airwallex.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenLocal()
	maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
	mavenCentral()
}

dependencies {
	implementation("com.airwallex.demo:book-api:0.0.1-SNAPSHOT")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
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
