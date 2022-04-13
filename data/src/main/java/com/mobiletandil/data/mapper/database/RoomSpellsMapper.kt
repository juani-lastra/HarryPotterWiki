package com.mobiletandil.data.mapper.database

import com.mobiletandil.data.database.entity.DBRoomSpells
import com.mobiletandil.domain.entity.Spell

fun Spell.transformToRoomSpells() = DBRoomSpells(
    id = this.id,
    name = this.name,
    incantation = this.incantation,
    effect = this.effect,
    canBeVerbal = this.canBeVerbal,
    type = this.type,
    light = this.light,
    creator = this.creator
)

fun DBRoomSpells.transformToSpells() = Spell(
    id = this.id,
    name = this.name,
    incantation = this.incantation,
    effect = this.effect,
    canBeVerbal = this.canBeVerbal,
    type = this.type,
    light = this.light,
    creator = this.creator
)
