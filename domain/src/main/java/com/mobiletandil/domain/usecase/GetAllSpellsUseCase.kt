package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.utils.ResponseResult

interface GetAllSpellsUseCase {
    operator fun invoke(): ResponseResult<List<Spell>>
}

class GetAllSpellsUseCaseImpl(private val service: HarryPotterService, private val database: HarryPotterDatabase) : GetAllSpellsUseCase {
    override fun invoke(): ResponseResult<List<Spell>> {
        return try {
            when (val response = service.getSpells()) {
                is ResponseResult.Success -> {
                    database.insertSpells(response.data)
                    ResponseResult.Success(database.getSpells())
                }
                is ResponseResult.Failure -> {
                    ResponseResult.Success(database.getSpells())
                }
            }
        } catch (e: Exception) {
            ResponseResult.Failure(e)
        }
    }
}
