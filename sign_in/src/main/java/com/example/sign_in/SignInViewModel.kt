package com.example.sign_in

import androidx.lifecycle.ViewModel
import com.example.common.domain.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class SignInViewModel(
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
}