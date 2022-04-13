package com.mobiletandil.domain.entity

data class Wizard(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val elixirs: List<Elixir>?
)
