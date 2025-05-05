package com.mine.repository

import com.mine.domain.Person
import com.mine.jooq.Tables.PERSON
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Result

@Singleton
class PersonRepository(
    @Inject
    private val dslContext: DSLContext
) {
    fun printSomething() {
        println("something")
    }

    fun getAllPersons(): Result<Record> {
        return dslContext.select().from(PERSON).fetch()
    }

    fun insertPerson(values: Person): MutableList<Person> {
        return dslContext.insertInto(PERSON, PERSON.ID, PERSON.NAME).values(
            values.id, values.name
        ).returning().fetchInto(Person::class.java)
    }
}