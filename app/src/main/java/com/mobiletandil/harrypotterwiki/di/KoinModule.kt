package com.mobiletandil.harrypotterwiki.di

import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailTabFragmentViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.HouseHeadsTabFragmentViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.HouseTraitsTabFragmentViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.SplashActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashActivityViewModel() }
    viewModel { MainActivityViewModel() }
    viewModel { HousesActivityViewModel(get()) }
    viewModel { HouseDetailTabFragmentViewModel() }
    viewModel { HouseHeadsTabFragmentViewModel() }
    viewModel { HouseTraitsTabFragmentViewModel() }
}
