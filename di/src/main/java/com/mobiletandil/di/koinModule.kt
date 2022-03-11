package com.mobiletandil.di

import com.mobiletandil.data.service.HarryPotterServiceImpl
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.usecase.GetOneHouseUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetOneHouseUseCase> { GetOneHouseUseCaseImpl(get()) }
}

val serviceModule = module {
    single<HarryPotterService> { HarryPotterServiceImpl() }
}
