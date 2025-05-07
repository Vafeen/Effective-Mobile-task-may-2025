package com.example.sign_in

/**
 * Состояние экрана SignIn.
 *
 * @property email Текущий введённый email пользователя.
 * @property password Текущий введённый пароль пользователя.
 * @property isButtonSignInActive Флаг, указывающий, активна ли кнопка входа.
 */
internal data class SignInState(
    val email: String = "",
    val password: String = "",
    val isButtonSignInActive: Boolean = false,
)
