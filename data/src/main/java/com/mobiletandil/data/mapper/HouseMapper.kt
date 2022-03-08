package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.HouseResultResponse
import com.mobiletandil.domain.entity.House

fun HouseResultResponse.transformToHouse() = House(
    id = this.id,
    name = this.name,
    houseColours = this.houseColours,
    founder = this.founder,
    animal = this.animal,
    element = this.element,
    ghost = this.ghost,
    commonRoom = this.commonRoom,
    heads = this.heads.map { it.transformToHead() },
    traits = this.traits.map { it.transformToTrait() }
)
