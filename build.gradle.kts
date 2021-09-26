import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.google.cloud.tools.jib") version "3.1.4"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"

}

group = "me.matt"
version = "0.0.1-SNAPSHOT"

jib {
    from {
        image = "adoptopenjdk:11-jre-hotspot"
    }
    to {
        image = "commute-subscribe:latest"
    }
    container {
        entrypoint = listOf("bash", "-c", "/entrypoint.sh")
        ports = listOf("8081")
        environment = mapOf(
            "SPRING_OUTPUT_ANSI_ENABLED" to "ALWAYS",
            "JHIPSTER_SLEEP" to "0"
        )
        creationTime = "USE_CURRENT_TIMESTAMP"
        user = "1000"
    }
    extraDirectories {
        permissions = mapOf(
            "/entrypoint.sh" to "755"
        )

        paths {
            "src/main/docker/jib"
        }

    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.twitter4j:twitter4j-stream:4.0.7")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
