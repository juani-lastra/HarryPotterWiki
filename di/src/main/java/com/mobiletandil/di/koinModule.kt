package com.mobiletandil.di

import androidx.room.Room
import com.mobiletandil.data.database.HarryPotterDatabaseImpl
import com.mobiletandil.data.service.HarryPotterServiceImpl
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetAllSpellsUseCase
import com.mobiletandil.domain.usecase.GetAllSpellsUseCaseImpl
import com.mobiletandil.domain.usecase.GetAllWizardsUseCase
import com.mobiletandil.domain.usecase.GetAllWizardsUseCaseImpl
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.usecase.GetOneHouseUseCaseImpl
import com.mobiletandil.domain.usecase.GetOneSpellUseCase
import com.mobiletandil.domain.usecase.GetOneSpellUseCaseImpl
import com.mobiletandil.domain.usecase.GetOneWizardsUseCase
import com.mobiletandil.domain.usecase.GetOneWizardsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetOneHouseUseCase> { GetOneHouseUseCaseImpl(get(), get()) }
    single<GetAllWizardsUseCase> { GetAllWizardsUseCaseImpl(get(), get()) }
    single<GetOneWizardsUseCase> { GetOneWizardsUseCaseImpl(get()) }
    single<GetAllSpellsUseCase> { GetAllSpellsUseCaseImpl(get(), get()) }
    single<GetOneSpellUseCase> { GetOneSpellUseCaseImpl(get()) }
}

val serviceModule = module {
    single<HarryPotterService> { HarryPotterServiceImpl() }
}

val databaseModule = module {
    single<HarryPotterDatabase> { Room.databaseBuilder(get(), HarryPotterDatabaseImpl::class.java, "HarryPotterWiki").build() }
    single { get<HarryPotterDatabaseImpl>().harryPotterDao() }
}
