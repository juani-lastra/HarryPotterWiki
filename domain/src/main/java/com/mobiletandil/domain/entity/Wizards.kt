package com.mobiletandil.domain.entity

import java.io.Serializable

data class Wizards(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val elixirs: List<Elixirs>?
): Serializable
