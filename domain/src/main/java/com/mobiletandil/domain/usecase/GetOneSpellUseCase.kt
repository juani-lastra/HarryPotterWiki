package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.utils.ResponseResult

interface GetOneSpellUseCase {
    operator fun invoke(spellID: String): ResponseResult<Spell>
}

class GetOneSpellUseCaseImpl(private val database: HarryPotterDatabase) : GetOneSpellUseCase {
    override fun invoke(spellID: String): ResponseResult<Spell> {
        return try {
            ResponseResult.Success(database.getOneSpell(spellID))
        } catch (e: Exception) {
            ResponseResult.Failure(e)
        }
    }
}
