package com.mobiletandil.data.service.response

import com.google.gson.annotations.SerializedName
import com.mobiletandil.domain.utils.Constants

data class SpellsResponse(
    @SerializedName("id")
    val id: String = Constants.EMPTY_STRING,

    @SerializedName("name")
    val name: String = Constants.EMPTY_STRING,

    @SerializedName("incantation")
    val incantation: String = Constants.EMPTY_STRING,

    @SerializedName("effect")
    val effect: String = Constants.EMPTY_STRING,

    @SerializedName("canBeVerbal")
    val canBeVerbal: Boolean = false,

    @SerializedName("type")
    val type: String = Constants.EMPTY_STRING,

    @SerializedName("light")
    val light: String = Constants.EMPTY_STRING,

    @SerializedName("creator")
    val creator: String = Constants.EMPTY_STRING,
)
