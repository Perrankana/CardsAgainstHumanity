package com.pandiandcode.cardsagainsthumanity

import android.app.Application
import com.pandiandcode.cardsagainsthumanity.di.databaseModule
import com.pandiandcode.cardsagainsthumanity.di.repositoryModule
import com.pandiandcode.cardsagainsthumanity.di.useCasesModule
import com.pandiandcode.cardsagainsthumanity.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class CahApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupAppDependencies()
    }

    private fun setupAppDependencies() {
        startKoin {
            androidLogger()
            androidContext(this@CahApplication)
            modules(appModules())
        }
    }

    private fun appModules(): List<Module> {
        return listOf(
            databaseModule,
            repositoryModule,

            useCasesModule,
            viewModelsModule
        )
    }
}