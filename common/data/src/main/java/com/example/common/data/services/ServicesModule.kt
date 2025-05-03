package com.example.common.data.services

import android.content.Context
import android.content.SharedPreferences
import com.example.common.domain.services.SettingsManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val ServicesModule = module {
    singleOf(::SettingsManagerImpl) {
        bind<SettingsManager>()
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            SharedPreferencesValue.NAME, Context.MODE_PRIVATE
        )
    }
}