package com.example.onboarding

import com.example.common.domain.navigation.Navigator
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val OnboardingModule = module {
    viewModel<OnboardingViewModel> { (navigator: Navigator) ->
        OnboardingViewModel(navigator, get())
    }
}