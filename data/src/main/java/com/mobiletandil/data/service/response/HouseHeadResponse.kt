package com.mobiletandil.data.service.response

import com.google.gson.annotations.SerializedName
import com.mobiletandil.domain.utils.Constants

data class HouseHeadResponse(
    @SerializedName("id")
    val id: String = Constants.EMPTY_STRING,

    @SerializedName("firstName")
    val firstName: String = Constants.EMPTY_STRING,

    @SerializedName("lastName")
    val lastName: String = Constants.EMPTY_STRING,
)
