package com.example.navigation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val NavRootModule = module {
    viewModelOf(::NavRootViewModel)
}