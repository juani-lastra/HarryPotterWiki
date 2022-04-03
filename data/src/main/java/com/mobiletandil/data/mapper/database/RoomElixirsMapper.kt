package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomElixirs
import com.mobiletandil.domain.entity.Elixirs

fun Elixirs.transformToRoomElixir() = DBRoomElixirs(
    id = this.id,
    name = this.name
)

fun DBRoomElixirs.transformToElixir() = Elixirs(
    id = this.id,
    name = this.name
)
