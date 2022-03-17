package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomHeads
import com.mobiletandil.domain.entity.HouseHead

fun HouseHead.transformToRoomHead() = DBRoomHeads(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName
)

fun DBRoomHeads.transformToHead() = HouseHead(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName
)
