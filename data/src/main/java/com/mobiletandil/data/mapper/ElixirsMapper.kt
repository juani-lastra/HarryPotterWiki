package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.ElixirsResponse
import com.mobiletandil.domain.entity.Elixir

fun ElixirsResponse.transformToElixirs() = Elixir(
    id = this.id,
    name = this.name
)
