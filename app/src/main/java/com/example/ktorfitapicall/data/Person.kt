package com.example.ktorfitapicall.data

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val films: List<String?>? = null,
    val homeworld: String? = null,
    val gender: String? = null,
    val skinColor: String? = null,
    val edited: String? = null,
    val created: String? = null,
    val mass: String? = null,
    val url: String? = null,
    val hairColor: String? = null,
    val birthYear: String? = null,
    val eyeColor: String? = null,
    val name: String? = null,
    val height: String? = null
)