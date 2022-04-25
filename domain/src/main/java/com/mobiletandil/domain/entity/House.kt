package com.mobiletandil.domain.entity

import com.mobiletandil.domain.utils.Constants.EMPTY_STRING

data class House(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val houseColours: String = EMPTY_STRING,
    val founder: String = EMPTY_STRING,
    val animal: String = EMPTY_STRING,
    val element: String = EMPTY_STRING,
    val ghost: String = EMPTY_STRING,
    val commonRoom: String = EMPTY_STRING,
    val heads: List<HouseHead> = emptyList(),
    val traits: List<Traits> = emptyList()
): java.io.Serializable
