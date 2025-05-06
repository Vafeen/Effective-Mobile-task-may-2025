package com.example.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.navigation.Navigator
import com.example.common.domain.navigation.Screen
import com.example.common.domain.services.SettingsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class NavRootViewModel(
    private val settingsManager: SettingsManager,
) : ViewModel(), Navigator {
    private val _effects = MutableSharedFlow<NavRootEffect>()
    val effects = _effects.asSharedFlow()
    private val _state = MutableStateFlow(
        NavRootState(
            startDestination = Screen.Onboarding
        )
    )
    val state = _state.asStateFlow()

    val screenWithBottomBar = listOf(
        Screen.Main, Screen.Favourites, Screen.Account
    )
    init {
        viewModelScope.launch {
            calculateStartScreen()
        }
    }

    override fun navigateTo(screen: Screen) {
        viewModelScope.launch(Dispatchers.IO) {
            _effects.emit(NavRootEffect.NavigateToScreen { navHostController ->
                navHostController.navigate(screen) {
                    Screen.Main::class.qualifiedName?.let { popUpTo(it) }
                    launchSingleTop = true
                }
            })
        }
    }

    override fun back() {
        viewModelScope.launch {
            _effects.emit(NavRootEffect.Back)
        }
    }

    private fun calculateStartScreen() {
        _state.update {
            it.copy(
                startDestination =
                    if (settingsManager.settingsFlow.value.isOnboardingShowed) Screen.SignIn else Screen.Onboarding
            )
        }
    }

    fun updateCurrentScreen(screen: Screen) {
        _state.update {
            it.copy(
                currentScreen = screen,
                isBottomBarVisible = screen in screenWithBottomBar
            )
        }
    }
}