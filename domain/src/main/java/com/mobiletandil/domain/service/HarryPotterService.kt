package com.mobiletandil.domain.service

import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.domain.utils.ResponseResult

interface HarryPotterService {
    fun getHouse(houseId: String): ResponseResult<House>
    fun getWizards(): ResponseResult<List<Wizards>>
}
