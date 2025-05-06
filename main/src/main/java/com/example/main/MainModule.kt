package com.example.main

import com.example.common.domain.navigation.Navigator
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    viewModel<MainViewModel> { (navigator: Navigator) ->
        MainViewModel(navigator)
    }
}