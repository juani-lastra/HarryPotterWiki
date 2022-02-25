package com.mobiletandil.harrypotterwiki.di

import com.mobiletandil.harrypotterwiki.viewmodel.SplashActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashActivityViewModel() }
}
