package com.demo.jdbc

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
//import org.jooq.DSLContext
//import org.jooq.SQLDialect
//import org.jooq.impl.DSL

@Factory
class AppConfig {

    @Singleton
    fun hikariDataSource(): HikariDataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = "jdbc:postgresql://localhost:5432/demo"
        hikariConfig.username = "tester"
        hikariConfig.driverClassName = "org.postgresql.Driver"
        return HikariDataSource(hikariConfig)
    }

//    @Singleton
//    @Named("dslContext")
//    fun dslContext(hikariDataSource: HikariDataSource): DSLContext {
//        return DSL.using(hikariDataSource, SQLDialect.POSTGRES)
//    }
}