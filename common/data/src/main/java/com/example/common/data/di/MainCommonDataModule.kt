package com.example.common.data.di

import com.example.common.data.services.ServicesModule
import org.koin.dsl.module

val MainCommonDataModule = module {
    includes(ServicesModule)
}