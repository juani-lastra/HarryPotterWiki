package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.SpellsResponse
import com.mobiletandil.domain.entity.Spell

fun SpellsResponse.transformToSpells() = Spell(
    id = this.id,
    name = this.name,
    incantation = this.incantation,
    effect = this.effect,
    canBeVerbal = this.canBeVerbal,
    type = this.type,
    light = this.light,
    creator = this.creator
)
