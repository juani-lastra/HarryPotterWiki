package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.TraitsResponse
import com.mobiletandil.domain.entity.Traits

fun TraitsResponse.transformToTrait() = Traits(
    id = this.id,
    name = this.name
)
