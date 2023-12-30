package com.example.news24.data.search

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)