//import org.jooq.meta.kotlin.*
plugins {
    id("application")
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.9.25-1.0.20"
    id("io.micronaut.aot") version "4.4.4"
    id("io.micronaut.application") version "4.4.4"

}
group = "com.demo"
application {
    mainClass = "com.demo.app.ApplicationKt"
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    implementation(project(":rootProject"))
    dependencies {
//    implementation("org.jooq:jooq:3.20.3")
//    implementation("org.jooq:jooq-codegen:3.20.3")
//    implementation("org.jooq:jooq-meta:3.20.3")
        implementation("org.postgresql:postgresql:42.7.4")
//        jooqGenerator("org.postgresql:postgresql:42.7.4")
        implementation("org.flywaydb:flyway-database-postgresql")
        ksp("io.micronaut:micronaut-http-validation")
        ksp("io.micronaut.serde:micronaut-serde-processor")
        implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
        implementation("io.micronaut.serde:micronaut-serde-jackson")
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.25")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.25")
        compileOnly("io.micronaut:micronaut-http-client")
        runtimeOnly("ch.qos.logback:logback-classic")
        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
        testImplementation("io.micronaut:micronaut-http-client")
        implementation("com.zaxxer:HikariCP:6.3.0")
        runtimeOnly("io.micronaut.sql:micronaut-jdbc-hikari")
    }
}
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("com.demo.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}