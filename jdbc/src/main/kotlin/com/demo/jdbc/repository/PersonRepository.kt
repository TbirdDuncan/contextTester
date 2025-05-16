package com.demo.repository

import jakarta.inject.Singleton
//import org.jooq.DSLContext
//import org.jooq.Record
//import org.jooq.Result

@Singleton
class PersonRepository(
//    @Inject
//    private val dslContext: DSLContext
) {
    fun printSomething() {
        println("something")
    }

//    fun getAllPersons(): Result<Record> {
//        return dslContext.select().from(PERSON).fetch()
//    }
//
//    fun getPerson(id: Int): Person {
//        return dslContext.select().from(PERSON).where(PERSON.ID.eq(id)).fetchInto(Person::class.java)
//    }
//
//    fun insertPerson(values: Person): MutableList<Person> {
//        return dslContext.insertInto(PERSON, PERSON.ID, PERSON.NAME).values(
//            values.id, values.name
//        ).returning().fetchInto(Person::class.java)
//    }
//
//    fun deletePerson(id: Int): Int {
//        return dslContext.deleteFrom(PERSON).where(PERSON.ID.eq(id)).execute()
//    }
//
//    fun updatePerson(id: Int, name: String): Int {
//        return dslContext.update(PERSON).set(PERSON.NAME, name).where(PERSON.ID.eq(id)).execute()
//    }
}