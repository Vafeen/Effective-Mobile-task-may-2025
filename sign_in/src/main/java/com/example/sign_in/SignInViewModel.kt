package com.example.sign_in

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.common.domain.constants.PasswordConstants
import com.example.common.domain.navigation.Navigator
import com.example.common.domain.navigation.Screen
import com.example.common.utils.openLink
import com.example.sign_in.utils.Link
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

internal class SignInViewModel(private val navigator: Navigator) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun signIn() {
        // *логика отправки данных на сервер*
        // Можно сделать сохранение локально, но EncryptedSharedPreferences
        // не рекомендовано использовать вообще, поскольку это не идет в бэкап системы, а в SharedPreferences хранить в открытом виде небезопасно
        // да и по тз на этом моменте указано только осуществление перехода на главный экран
        // Надеюсь, минусов за это не будет :)
        navigator.navigateTo(Screen.BottomBarScreens)
    }

    fun openVk(context: Context) = context.openLink(Link.VK_LINK)
    fun openOk(context: Context) = context.openLink(Link.OK_LINK)
    fun processEmail(email: String) {
        _state.update { it.copy(email = email) }
        updateButtonSignInState()
    }

    fun processPassword(password: String) {
        _state.update { it.copy(password = password) }
        updateButtonSignInState()
    }

    // Проверка email по маске "текст@текст.текст"
    private fun String.isValidEmail(): Boolean =
        Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$", this)

    private fun updateButtonSignInState() {
        _state.update {
            it.copy(isButtonSignInActive = it.password.length >= PasswordConstants.MIN_PASSWORD_LEN && it.email.isValidEmail())
        }
    }
}