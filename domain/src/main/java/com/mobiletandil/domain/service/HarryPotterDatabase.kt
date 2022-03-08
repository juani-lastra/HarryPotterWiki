package com.mobiletandil.domain.service

import com.mobiletandil.domain.entity.House

interface HarryPotterDatabase {
    fun getHouse(houseId: String): House
    fun insertHouse(house: House)
}
