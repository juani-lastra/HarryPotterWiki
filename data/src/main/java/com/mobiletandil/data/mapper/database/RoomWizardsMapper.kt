package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomWizards
import com.mobiletandil.domain.entity.Wizards

fun Wizards.transformToRoomWizards() = DBRoomWizards(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    elixirs = this.elixirs?.map { it.transformToRoomElixir() }
)

fun DBRoomWizards.transformToWizards() = Wizards(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    elixirs = this.elixirs?.map { it.transformToElixir() }
)
