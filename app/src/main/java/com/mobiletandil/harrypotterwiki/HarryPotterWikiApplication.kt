package com.mobiletandil.harrypotterwiki

import android.app.Application
import com.mobiletandil.di.databaseModule
import com.mobiletandil.di.serviceModule
import com.mobiletandil.di.useCaseModule
import com.mobiletandil.harrypotterwiki.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class HarryPotterWikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HarryPotterWikiApplication)
            modules(viewModelModule, useCaseModule, serviceModule, databaseModule)
        }
    }
}
