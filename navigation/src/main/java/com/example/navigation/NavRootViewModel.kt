package com.example.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.navigation.Navigator
import com.example.common.domain.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class NavRootViewModel : ViewModel(), Navigator {
    private val _effects = MutableSharedFlow<NavRootEffect>()
    val effects = _effects.asSharedFlow()
    private val _state = MutableStateFlow(
        NavRootState(
            startDestination = Screen.Onboarding
        )
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            calculateStartScreen()
        }
    }

    override fun navigateTo(screen: Screen) {
        viewModelScope.launch {
            _effects.emit(NavRootEffect.NavigateToScreen(screen))
        }
    }

    override fun back() {
        viewModelScope.launch {
            _effects.emit(NavRootEffect.Back)
        }
    }

    private fun calculateStartScreen() {
        _state.update { it.copy(startDestination = Screen.Onboarding) }
    }
}