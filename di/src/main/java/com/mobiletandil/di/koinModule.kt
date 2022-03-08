package com.mobiletandil.di

import androidx.room.Room
import com.mobiletandil.data.database.HarryPotterDatabaseImpl
import com.mobiletandil.data.service.HarryPotterServiceImpl
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.usecase.GetOneHouseUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetOneHouseUseCase> { GetOneHouseUseCaseImpl(get(), get()) }
}

val serviceModule = module {
    single<HarryPotterService> { HarryPotterServiceImpl() }
}

val databaseModule = module {
    single<HarryPotterDatabase> { Room.databaseBuilder(get(), HarryPotterDatabaseImpl::class.java, "HarryPotterWiki").build() }
    single { get<HarryPotterDatabaseImpl>().harryPotterDao() }
}
