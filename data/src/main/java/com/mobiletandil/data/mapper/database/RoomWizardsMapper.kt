package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomWizards
import com.mobiletandil.domain.entity.Wizard

fun Wizard.transformToRoomWizards() = DBRoomWizards(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    elixirs = this.elixirs?.map { it.transformToRoomElixir() }
)

fun DBRoomWizards.transformToWizard() = Wizard(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    elixirs = this.elixirs?.map { it.transformToElixir() }
)
