package com.mobiletandil.data.service.response

import com.google.gson.annotations.SerializedName
import com.mobiletandil.domain.utils.Constants

data class WizardsResponse(
    @SerializedName("id")
    val id: String = Constants.EMPTY_STRING,

    @SerializedName("firstName")
    val firstName: String = Constants.EMPTY_STRING,

    @SerializedName("lastName")
    val lastName: String = Constants.EMPTY_STRING,

    @SerializedName("elixirs")
    val elixirs: List<ElixirsResponse> = emptyList()
)
