package com.example.sign_in

import com.example.common.domain.navigation.Navigator
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val SignInModule = module {
    viewModel<SignInViewModel> { (navigator: Navigator) ->
        SignInViewModel(navigator)
    }
}