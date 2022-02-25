package com.mobiletandil.harrypotterwiki

import android.app.Application
import com.mobiletandil.harrypotterwiki.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class HarryPotterWikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule)
        }
    }
}
