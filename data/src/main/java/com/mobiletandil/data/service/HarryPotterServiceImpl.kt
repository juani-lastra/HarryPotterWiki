package com.mobiletandil.data.service

import com.mobiletandil.data.mapper.transformToHouse
import com.mobiletandil.data.service.api.HarryPotterApi
import com.mobiletandil.data.service.response.HouseResultResponse
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.utils.ResponseResult

class HarryPotterServiceImpl : HarryPotterService {
    private val requestResponse = RequestGenerator().createService(HarryPotterApi::class.java)

    override fun getHouse(houseId: String): ResponseResult<House> {
        return try {
            val response = requestResponse.getHouse(houseId).execute()
            return if (response.isSuccessful) {
                val body = response.body() as HouseResultResponse
                return ResponseResult.Success(body.transformToHouse())
            } else {
                ResponseResult.Failure(Exception(EXCEPTION_MESSAGE))
            }
        } catch (e: Exception) {
            ResponseResult.Failure(Exception(e))
        }
    }

    companion object {
        private const val EXCEPTION_MESSAGE: String = "No Data Received, Bad Response"
    }
}
