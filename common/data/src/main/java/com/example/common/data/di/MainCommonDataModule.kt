package com.example.common.data.di

import com.example.common.data.coroutines.CoroutinesModule
import com.example.common.data.network.NetworkModule
import com.example.common.data.network.NetworkUseCaseModule
import com.example.common.data.services.ServicesModule
import org.koin.dsl.module

val MainCommonDataModule = module {
    includes(
        ServicesModule,
        NetworkModule,
        NetworkUseCaseModule,
        CoroutinesModule
    )
}