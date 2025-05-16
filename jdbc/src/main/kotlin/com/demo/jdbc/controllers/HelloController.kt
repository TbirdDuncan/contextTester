package com.demo.jdbc.controllers

import com.demo.repository.PersonRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/persons") // (1)
class HelloController(
    private val personRepository: PersonRepository
) {

    @Get(produces = [MediaType.TEXT_PLAIN]) // (2)
    fun index(): String {
        personRepository.printSomething()
//        print(personRepository.getAllPersons())
        return "Hello World" // (3)
    }

//    @Post
//    fun createPerson(@Body person: Person): MutableList<Person> {
//        return personRepository.insertPerson(person)
//    }
//
//    @Get("/{id}",produces = [MediaType.APPLICATION_JSON])
//    fun getPerson(@PathVariable("id") id: Int): Person {
//        return personRepository.getPerson(id)
//    }
//    @Delete("/{id}")
//    fun deletePerson(@PathVariable("id") id: Int): Int {
//        return personRepository.deletePerson(id)
//    }
//    @Put("/{id}")
//    fun updatePerson(@PathVariable("id") id: Int, @Body person: Person): Int {
//        return personRepository.updatePerson(id, person.name)
//    }
}