package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.utils.Constants
import com.mobiletandil.domain.utils.Houses
import com.mobiletandil.domain.utils.HousesIds
import com.mobiletandil.domain.utils.ResponseResult

interface GetOneHouseUseCase {
    operator fun invoke(house: Houses): ResponseResult<House>
}

class GetOneHouseUseCaseImpl(private val service: HarryPotterService) : GetOneHouseUseCase {
    override fun invoke(house: Houses): ResponseResult<House> {
        val houseId: String = when (house) {
            Houses.GRYFFINDOR_HOUSE -> HousesIds.GRYFFINDOR_HOUSE
            Houses.HUFFLEPUFF_HOUSE -> HousesIds.HUFFLEPUFF_HOUSE
            Houses.RAVENCLAW_HOUSE -> HousesIds.RAVENCLAW_HOUSE
            Houses.SLYTHERIN_HOUSE -> HousesIds.SLYTHERIN_HOUSE
            else -> {
                Constants.EMPTY_STRING
            }
        }
        return service.getHouse(houseId)
    }
}
