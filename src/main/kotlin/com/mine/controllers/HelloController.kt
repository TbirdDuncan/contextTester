package com.mine.controllers

import com.mine.domain.Person
import com.mine.repository.PersonRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/hello") // (1)
class HelloController(
    private val personRepository: PersonRepository
) {

    @Get(produces = [MediaType.TEXT_PLAIN]) // (2)
    fun index(): String {
        personRepository.printSomething()
        print(personRepository.getAllPersons())
        return "Hello World" // (3)
    }

    @Post
    fun createPerson(@Body person: Person): MutableList<Person> {
        return personRepository.insertPerson(person)
    }
}