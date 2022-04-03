package com.mobiletandil.data.service.response

import com.google.gson.annotations.SerializedName
import com.mobiletandil.domain.utils.Constants

data class ElixirsResponse(
    @SerializedName("id")
    val id: String = Constants.EMPTY_STRING,

    @SerializedName("name")
    val name: String = Constants.EMPTY_STRING
)
