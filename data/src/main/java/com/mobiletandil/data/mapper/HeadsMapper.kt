package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.HouseHeadResponse
import com.mobiletandil.domain.entity.HouseHead

fun HouseHeadResponse.transformToHead() = HouseHead(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName
)
