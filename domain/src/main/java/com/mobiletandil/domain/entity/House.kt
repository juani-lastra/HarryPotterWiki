package com.mobiletandil.domain.entity

data class House(
    val id: String,
    val name: String,
    val houseColours: String,
    val founder: String,
    val animal: String,
    val element: String,
    val ghost: String,
    val commonRoom: String,
    val heads: List<HouseHead>,
    val traits: List<Traits>
): java.io.Serializable
