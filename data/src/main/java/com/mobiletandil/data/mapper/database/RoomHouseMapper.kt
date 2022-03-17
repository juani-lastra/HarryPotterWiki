package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomHouses
import com.mobiletandil.domain.entity.House

fun House.transformToRoomHouse() = DBRoomHouses(
    id = this.id,
    name = this.name,
    houseColours = this.houseColours,
    founder = this.founder,
    animal = this.animal,
    element = this.element,
    ghost = this.ghost,
    commonRoom = this.commonRoom,
    heads = this.heads.map { it.transformToRoomHead() },
    traits = this.traits.map { it.transformToRoomTrait() }
)

fun DBRoomHouses.transformToHouse() = House(
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
