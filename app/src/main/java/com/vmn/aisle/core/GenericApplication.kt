package com.vmn.aisle.core

import android.app.Application
import com.vmn.aisle.di.appModule
import com.vmn.data.di.dataModule
import com.vmn.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GenericApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(dataModule, domainModule, appModule)
            androidContext(this@GenericApplication)
        }
    }
}