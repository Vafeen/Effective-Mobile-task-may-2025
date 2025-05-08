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

/**
 * ViewModel для управления навигацией в корневом навигационном графе приложения.
 *
 * Реализует интерфейс [Navigator] для управления переходами между экранами.
 *
 * @property settingsManager менеджер настроек пользователя, используется для определения стартового экрана.
 */
internal class NavRootViewModel(
    private val settingsManager: SettingsManager,
) : ViewModel(), Navigator {

    /** Поток эффектов навигации, таких как переходы между экранами */
    private val _effects = MutableSharedFlow<NavRootEffect>()

    /** Публичный поток эффектов навигации для подписки */
    val effects = _effects.asSharedFlow()

    /** Внутренний поток состояния навигации */
    private val _state = MutableStateFlow(
        NavRootState(
            startDestination = Screen.Onboarding
        )
    )

    /** Публичный поток состояния навигации */
    val state = _state.asStateFlow()

    /** Список экранов, для которых отображается нижняя панель навигации */
    val screenWithBottomBar = listOf(
        Screen.Main, Screen.Favourites, Screen.Account
    )

    init {
        // Вычисляем стартовый экран при инициализации ViewModel
        calculateStartScreen()
    }

    /**
     * Выполняет навигацию к указанному экрану.
     *
     * @param screen экран, к которому нужно перейти.
     *
     * Логика работы:
     * 1. Отправляет эффект навигации с переходом к экрану.
     * 2. При переходе выполняет popUpTo до главного экрана и устанавливает launchSingleTop для избежания дублирования.
     */
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

    /**
     * Обрабатывает действие "назад" в навигации.
     */
    override fun back() {
        viewModelScope.launch {
            _effects.emit(NavRootEffect.Back)
        }
    }

    /**
     * Вычисляет стартовый экран приложения на основе настроек пользователя.
     *
     * Логика работы:
     * - Если онбординг не показан, стартовый экран - Onboarding.
     * - Если пользователь не вошёл в систему - экран входа SignIn.
     * - Иначе - главный экран с нижней панелью BottomBarScreens.
     */
    private fun calculateStartScreen() {
        val settings = settingsManager.settingsFlow.value
        _state.update {
            it.copy(
                startDestination = when {
                    !settings.isOnboardingShowed -> Screen.Onboarding
                    !settings.isSignedIn -> Screen.SignIn
                    else -> Screen.BottomBarScreens
                }
            )
        }
    }

    /**
     * Обновляет текущий отображаемый экран и видимость нижней панели навигации.
     *
     * @param screen текущий экран.
     */
    fun updateCurrentScreen(screen: Screen) {
        _state.update {
            it.copy(
                currentScreen = screen,
                isBottomBarVisible = screen in screenWithBottomBar
            )
        }
    }
}
