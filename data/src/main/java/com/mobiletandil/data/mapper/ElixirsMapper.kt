package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.ElixirsResponse
import com.mobiletandil.domain.entity.Elixirs

fun ElixirsResponse.transformToElixirs() = Elixirs(
    id = this.id,
    name = this.name
)
