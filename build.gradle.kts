import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "xyz.omnia96"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.squareup.okhttp3:okhttp:3.14.2")
	implementation("com.github.salomonbrys.kotson:kotson:2.5.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	compile("org.springframework.boot:spring-boot-starter-thymeleaf")
	compile("org.springframework.boot:spring-boot-starter-jdbc")
	compile("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.0.1")
	compile("com.microsoft.sqlserver:mssql-jdbc:7.2.2.jre11")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
