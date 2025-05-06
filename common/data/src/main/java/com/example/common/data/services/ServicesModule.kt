package com.example.common.data.services

import android.content.Context
import android.content.SharedPreferences
import com.example.common.data.services.courses_service.RetrofitCoursesService
import com.example.common.domain.services.SettingsManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val ServicesModule = module {
    singleOf(::SettingsManagerImpl) {
        bind<SettingsManager>()
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            SharedPreferencesValue.NAME, Context.MODE_PRIVATE
        )
    }
    single<RetrofitCoursesService> {
        Retrofit.Builder().baseUrl(RetrofitCoursesService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitCoursesService::class.java)
    }
}