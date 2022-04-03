package com.mobiletandil.domain.service

import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.Wizards

interface HarryPotterDatabase {
    fun getHouse(houseId: String): House
    fun insertHouse(house: House)
    fun insertWizards(wizards: List<Wizards>)
    fun getAllWizards(): List<Wizards>
}
