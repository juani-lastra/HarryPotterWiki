package com.mobiletandil.data.service.response

import com.google.gson.annotations.SerializedName
import com.mobiletandil.domain.utils.Constants.EMPTY_STRING

data class HouseResultResponse(
    @SerializedName("id")
    val id: String = EMPTY_STRING,

    @SerializedName("name")
    val name: String = EMPTY_STRING,

    @SerializedName("houseColours")
    val houseColours: String = EMPTY_STRING,

    @SerializedName("founder")
    val founder: String = EMPTY_STRING,

    @SerializedName("animal")
    val animal: String = EMPTY_STRING,

    @SerializedName("element")
    val element: String = EMPTY_STRING,

    @SerializedName("ghost")
    val ghost: String = EMPTY_STRING,

    @SerializedName("commonRoom")
    val commonRoom: String = EMPTY_STRING,

    @SerializedName("heads")
    val heads: List<HouseHeadResponse> = emptyList(),

    @SerializedName("traits")
    val traits: List<TraitsResponse> = emptyList()
)
