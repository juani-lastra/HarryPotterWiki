package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.utils.ResponseResult

interface GetOneWizardsUseCase {
    operator fun invoke(wizardID: String): ResponseResult<Wizard>
}

class GetOneWizardsUseCaseImpl(private val database: HarryPotterDatabase) : GetOneWizardsUseCase {
    override fun invoke(wizardID: String): ResponseResult<Wizard> {
        return try {
            ResponseResult.Success(database.getOneWizard(wizardID))
        } catch (e: Exception) {
            ResponseResult.Failure(e)
        }
    }
}
