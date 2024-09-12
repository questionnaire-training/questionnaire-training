plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
     id("io.micronaut.application") version "4.4.2"
}
version = "0.1"
group = "training.questionnaire.app"
repositories {
    mavenCentral()
}
dependencies {
    implementation(project(":code"))
    runtimeOnly("ch.qos.logback:logback-classic")
}
application {
    mainClass = "training.questionnaire.app.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}
graalvmNative {
    toolchainDetection.set(true)
    binaries {
        all {
            buildArgs.add("--strict-image-heap")
        }
        named("main") {
            imageName.set("qt")
        }
    }
}
micronaut {
    runtime("netty")
    testRuntime("junit5")
     processing {
        incremental(true)
        annotations("training.questionnaire.*")
    }
}
tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}