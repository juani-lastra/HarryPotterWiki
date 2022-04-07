package com.mobiletandil.domain.entity

import java.io.Serializable

data class Spells(
    val id: String,
    val name: String,
    val incantation: String?,
    val effect: String?,
    val canBeVerbal: Boolean?,
    val type: String?,
    val light: String?,
    val creator: String?
): Serializable
