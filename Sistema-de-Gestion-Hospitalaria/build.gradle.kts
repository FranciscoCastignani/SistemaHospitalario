plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly ("org.projectlombok:lombok:1.18.38")
    annotationProcessor ("org.projectlombok:lombok:1.18.38")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // JPA API
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    // Implementación de JPA (Hibernate)
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    // Logger (opcional pero recomendable)
    implementation("org.slf4j:slf4j-simple:2.0.13")
    // Base de datos en memoria H2
    runtimeOnly("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}