package com.mobiletandil.data.service

import com.mobiletandil.data.mapper.transformToHouse
import com.mobiletandil.data.mapper.transformToSpells
import com.mobiletandil.data.mapper.transformToWizards
import com.mobiletandil.data.service.api.HarryPotterApi
import com.mobiletandil.data.service.response.HouseResultResponse
import com.mobiletandil.data.service.response.SpellsResponse
import com.mobiletandil.data.service.response.WizardsResponse
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.entity.Wizards
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

    override fun getWizards(): ResponseResult<List<Wizards>> {
        return try {
            val response = requestResponse.getAllWizards().execute()
            return if (response.isSuccessful) {
                val body = response.body() as List<WizardsResponse>
                return ResponseResult.Success(body.map { it.transformToWizards() })
            } else {
                ResponseResult.Failure(Exception(EXCEPTION_MESSAGE))
            }
        } catch (e: Exception) {
            ResponseResult.Failure(Exception(e))
        }
    }

    override fun getSpells(): ResponseResult<List<Spells>> {
        return try {
            val response = requestResponse.getAllSpells().execute()
            return if (response.isSuccessful) {
                val body = response.body() as List<SpellsResponse>
                return ResponseResult.Success(body.map { it.transformToSpells() })
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
