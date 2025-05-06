package com.example.main

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val MainModule = module {
    viewModelOf(::MainViewModel)
}