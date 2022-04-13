package com.mobiletandil.data.mapper

import com.mobiletandil.data.service.response.WizardsResponse
import com.mobiletandil.domain.entity.Wizard

fun WizardsResponse.transformToWizards() = Wizard(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    elixirs = this.elixirs.map { it.transformToElixirs() }
)
