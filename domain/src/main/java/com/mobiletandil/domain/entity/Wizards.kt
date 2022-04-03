package com.mobiletandil.domain.entity

data class Wizards(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val elixirs: List<Elixirs>?
)
