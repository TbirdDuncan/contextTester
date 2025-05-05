package com.mine.domain;

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class Person(
    val id: Int,
    val name: String
)