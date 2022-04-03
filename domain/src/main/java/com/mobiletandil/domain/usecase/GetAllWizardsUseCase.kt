package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.utils.ResponseResult

interface GetAllWizardsUseCase {
    operator fun invoke(): ResponseResult<List<Wizards>>
}

class GetAllWizardsUseCaseImpl(private val service: HarryPotterService, private val database: HarryPotterDatabase) : GetAllWizardsUseCase {
    override fun invoke(): ResponseResult<List<Wizards>> {
        return try {
            when (val response = service.getWizards()) {
                is ResponseResult.Success -> {
                    database.insertWizards(response.data)
                    ResponseResult.Success(database.getAllWizards())
                }
                is ResponseResult.Failure -> {
                    ResponseResult.Success(database.getAllWizards())
                }
            }
        } catch (e: Exception) {
            println(e)
            ResponseResult.Failure(e)
        }
    }
}
