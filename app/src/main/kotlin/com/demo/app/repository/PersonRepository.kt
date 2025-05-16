package com.demo.app.repository

import jakarta.inject.Singleton

@Singleton
class PersonRepository(
//    @Inject
//    private val dslContext: DSLContext
) {
    fun printSomething() {
        println("something")
    }
}