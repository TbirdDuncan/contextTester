import org.jooq.meta.kotlin.*

buildscript {
    repositories {
        mavenCentral()
    }
}
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.25"
    id("com.google.devtools.ksp") version "1.9.25-1.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.4"
    id("io.micronaut.aot") version "4.4.4"
    id("org.flywaydb.flyway") version "9.22.0"
//    id("org.jooq.jooq-codegen-gradle") version "3.20.3"
    id("nu.studer.jooq") version "10.1"

}

version = "0.1"
group = "com.mine"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.jooq:jooq:3.20.3")
//    implementation("org.jooq:jooq-codegen:3.20.3")
//    implementation("org.jooq:jooq-meta:3.20.3")
    implementation("org.postgresql:postgresql:42.7.4")
    jooqGenerator("org.postgresql:postgresql:42.7.4")
    implementation("org.flywaydb:flyway-database-postgresql")
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("io.micronaut:micronaut-http-client")
    implementation("com.zaxxer:HikariCP:6.3.0")
    runtimeOnly("io.micronaut.sql:micronaut-jdbc-hikari")
}


application {
    mainClass = "com.mine.ApplicationKt"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}

graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("com.mine.*")
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
flyway {
    url = "jdbc:postgresql://localhost:5432/demo"
    user = "tester"
    schemas = arrayOf<String>("public")
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

jooq {
    version.set("3.20.3")  // default (can be omitted)
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)  // default (can be omitted)

    configurations {
        create("main") {  // name of the jOOQ configuration
            generateSchemaSourceOnCompilation.set(true)  // default (can be omitted)

            jooqConfiguration {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/demo"
                    user = "tester"
                    properties {
                        property {
                            key = "ssl"
                            value = "false"
                        }
                    }
                }
                generator {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        forcedTypes {
                            forcedType {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "JSONB?"
                            }
                            forcedType {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "INET"
                            }
                        }
                    }
                    generate {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target {
                        packageName = "com.mine.jooq"
                        directory = "build/generated-src/jooq/main"  // default (can be omitted)
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}