package com.mobiletandil.domain.usecase

import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.utils.ResponseResult

interface GetOneWizardUseCase {
    operator fun invoke(wizardID: String): ResponseResult<Wizard>
}

class GetOneWizardUseCaseImpl(private val database: HarryPotterDatabase) : GetOneWizardUseCase {
    override fun invoke(wizardID: String): ResponseResult<Wizard> = ResponseResult.Success(database.getOneWizard(wizardID))
}
