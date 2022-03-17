package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomTraits
import com.mobiletandil.domain.entity.Traits

fun Traits.transformToRoomTrait() = DBRoomTraits(
    id = this.id,
    name = this.name
)

fun DBRoomTraits.transformToTrait() = Traits(
    id = this.id,
    name = this.name
)
