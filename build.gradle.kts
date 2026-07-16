plugins {
    java
    id("org.springframework.boot") version "3.5.15"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.blubugtech.com"
version = "1.0.0"
description = "API Gateway for routing requests to Bakery microservices"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2025.0.3"

dependencies {
    // 2. Spring Boot Core & Web
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // 3. Spring Cloud & Discovery
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux") // updated to non-deprecated gateway
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
//    implementation("org.springframework.cloud:spring-cloud-starter-config")

    // 4. Data & Persistence
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // 7. Third-Party Utilities (Jackson, AWS, etc.)
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // 8. Tooling & Lombok
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // 9. Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
