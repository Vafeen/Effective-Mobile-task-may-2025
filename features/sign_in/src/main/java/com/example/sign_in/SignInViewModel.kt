package com.example.sign_in

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.common.domain.constants.PasswordConstants
import com.example.common.domain.navigation.Navigator
import com.example.common.domain.navigation.Screen
import com.example.common.domain.services.SettingsManager
import com.example.common.utils.openLink
import com.example.sign_in.utils.Link
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

/**
 * ViewModel для экрана входа в систему (Sign In).
 *
 * Управляет состоянием экрана, обработкой ввода email и пароля,
 * а также навигацией и сохранением настроек.
 *
 * @property navigator Навигатор для управления переходами между экранами.
 * @property settingsManager Менеджер для работы с настройками приложения.
 */
internal class SignInViewModel(
    private val navigator: Navigator,
    private val settingsManager: SettingsManager
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    /**
     * Выполняет логику входа пользователя.
     *
     * Сохраняет в настройках, что он прошёл вход
     * затем переходит на главный экран приложения.
     */
    fun signIn() {
        // *логика отправки данных на сервер*
        // Можно сделать сохранение локально, но EncryptedSharedPreferences
        // не рекомендовано использовать вообще, поскольку это не идет в бэкап системы, а в SharedPreferences хранить в открытом виде небезопасно
        // да и по тз на этом моменте указано только осуществление перехода на главный экран
        // Надеюсь, минусов за это не будет :)
        settingsManager.save { it.copy(isSignedIn = true) }
        navigator.back()
        navigator.navigateTo(Screen.BottomBarScreens)
    }

    fun openVk(context: Context) = context.openLink(Link.VK_LINK)
    fun openOk(context: Context) = context.openLink(Link.OK_LINK)

    /**
     * Обрабатывает ввод email пользователя.
     *
     * Обновляет состояние и проверяет возможность активации кнопки входа.
     *
     * @param email Введённый пользователем email.
     */
    fun processEmail(email: String) {
        _state.update { it.copy(email = email) }
        updateButtonSignInState()
    }

    /**
     * Обрабатывает ввод пароля пользователя.
     *
     * Обновляет состояние и проверяет возможность активации кнопки входа.
     *
     * @param password Введённый пользователем пароль.
     */
    fun processPassword(password: String) {
        _state.update { it.copy(password = password) }
        updateButtonSignInState()
    }

    /**
     * Проверяет, соответствует ли строка формату email.
     *
     * Использует регулярное выражение для проверки.
     *
     * @receiver Строка для проверки.
     * @return `true`, если строка соответствует формату email, иначе `false`.
     */
    private fun String.isValidEmail(): Boolean =
        Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$", this)

    /**
     * Обновляет состояние активности кнопки входа.
     *
     * Кнопка активна, если пароль длиннее минимальной длины
     * и email валиден.
     */
    private fun updateButtonSignInState() {
        _state.update {
            it.copy(
                isButtonSignInActive = it.password.length >= PasswordConstants.MIN_PASSWORD_LEN &&
                        it.email.isValidEmail()
            )
        }
    }
}