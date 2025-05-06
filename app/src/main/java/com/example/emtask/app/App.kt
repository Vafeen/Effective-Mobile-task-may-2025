package com.example.emtask.app

import android.app.Application
import com.example.common.data.di.MainCommonDataModule
import com.example.main.MainModule
import com.example.navigation.NavRootModule
import com.example.onboarding.OnboardingModule
import com.example.sign_in.SignInModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                MainCommonDataModule,
                NavRootModule,
                OnboardingModule,
                SignInModule,
                MainModule,
            )
        }
    }
}