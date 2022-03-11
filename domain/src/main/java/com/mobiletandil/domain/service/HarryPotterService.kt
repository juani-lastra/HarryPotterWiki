package com.mobiletandil.domain.service

import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.utils.Houses
import com.mobiletandil.domain.utils.ResponseResult

interface HarryPotterService {
    fun getHouse(houseId: String): ResponseResult<House>
}
