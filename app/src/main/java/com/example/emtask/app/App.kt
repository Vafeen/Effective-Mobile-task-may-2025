package com.example.emtask.app

import android.app.Application
import com.example.common.data.services.ServicesModule
import com.example.navigation.NavRootModule
import com.example.onboarding.OnboardingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                NavRootModule,
                OnboardingModule,
                ServicesModule
            )
        }
    }
}