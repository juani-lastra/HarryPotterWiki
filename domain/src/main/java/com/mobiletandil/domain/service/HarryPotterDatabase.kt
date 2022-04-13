package com.mobiletandil.domain.service

import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.entity.Wizard

interface HarryPotterDatabase {
    fun getHouse(houseId: String): House
    fun insertHouse(house: House)
    fun insertWizards(wizards: List<Wizard>)
    fun getAllWizards(): List<Wizard>
    fun getOneWizard(wizardID: String): Wizard
    fun insertSpells(spells: List<Spell>)
    fun getSpells(): List<Spell>

}
