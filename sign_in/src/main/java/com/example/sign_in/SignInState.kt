package com.example.sign_in

internal data class SignInState(
    val email: String = "",
    val password: String = "",
    val isButtonSignInActive: Boolean = false,
)
