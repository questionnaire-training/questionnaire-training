plugins {
    `java-library`
    jacoco
    id("org.graalvm.buildtools.native") version "0.10.3"
}
repositories {
    mavenCentral()
}
dependencies {
    // BOM
    annotationProcessor(platform(libs.micronaut.platform))
    implementation(platform(libs.micronaut.platform))
    testAnnotationProcessor(platform(libs.micronaut.platform))
    testImplementation(platform(libs.micronaut.platform))

    annotationProcessor(libs.micronaut.inject.java)

    // Server
    implementation(libs.micronaut.http.server)

    // Micronaut Validation
    annotationProcessor(libs.micronaut.validation.processor)
    testAnnotationProcessor(libs.micronaut.validation.processor)
    implementation(libs.micronaut.validation)

    // Micronaut Serialization
    annotationProcessor(libs.micronaut.serde.processor)
    implementation(libs.micronaut.serde.jackson)

    // Micronaut Data
    annotationProcessor(libs.micronaut.data.processor)
    implementation(libs.micronaut.data.jdbc)
    // MYSQL
    implementation(libs.mysql.connector.j)
    // Connection Pool
    implementation(libs.micronaut.jdbc.hikari)

    // Database Migration Tool
    runtimeOnly(libs.micronaut.liquibase)

    // ID Generation
    implementation(libs.ksuid)

    // Micronaut OpenAPI
    annotationProcessor(libs.micronaut.openapi)
    compileOnly(libs.micronaut.openapi.annotations)

    // Micronaut Views
    api(libs.micronaut.views.thymeleaf)
    implementation(libs.micronaut.views.fieldset)

    // Micronaut Test JUnit 5
    testAnnotationProcessor(libs.micronaut.inject.java)
    testImplementation(libs.micronaut.test.junit5)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testRuntimeOnly(libs.logback.classic)

    testImplementation(libs.testcontainers.mysql)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)

    // Test Server Runtime
    testImplementation(libs.micronaut.http.server.netty)

    // HTTP Client
    testImplementation(libs.micronaut.http.client)
}
tasks.test {
    useJUnitPlatform()
}
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
tasks.jacocoTestCoverageVerification {
    enabled = true
    violationRules {
        rule {
            limit {
                minimum = "0".toBigDecimal()
            }
        }
    }
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}
graalvmNative {
    toolchainDetection.set(true)
}