package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomElixirs
import com.mobiletandil.domain.entity.Elixir

fun Elixir.transformToRoomElixir() = DBRoomElixirs(
    id = this.id,
    name = this.name
)

fun DBRoomElixirs.transformToElixir() = Elixir(
    id = this.id,
    name = this.name
)
