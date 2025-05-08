package com.example.onboarding

import androidx.lifecycle.ViewModel
import com.example.common.domain.navigation.Navigator
import com.example.common.domain.navigation.Screen
import com.example.common.domain.services.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel для экрана онбординга.
 *
 * Управляет списком курсов для отображения, навигацией и сохранением состояния прохождения онбординга.
 *
 * @property navigator объект для управления навигацией между экранами.
 * @property settingsManager менеджер настроек пользователя для сохранения состояния онбординга.
 */
internal class OnboardingViewModel(
    private val navigator: Navigator,
    private val settingsManager: SettingsManager
) : ViewModel() {

    /** Список курсов, отображаемых на экране онбординга */
    private val courses = listOf(
        Course(name = "1С Администрирование"),
        Course(name = "RabbitMQ", degreesIncline = -45f),
        Course(name = "Трафик"),
        Course(name = "Маркетинг"),
        Course(name = "B2B маркетинг"),
        Course(name = "Google Аналитика"),
        Course(name = "UX Исследователь"),
        Course(name = "Веб-аналитика"),
        Course(name = "Big Data", degreesIncline = 45f),
        Course(name = "Дизайн"),
        Course(name = "Веб-дизайн"),
        Course(name = "Cinema 4D"),
        Course(name = "Промпт ин..."),
        Course(name = "Tree.js", degreesIncline = -45f),
        Course(name = "Парсинг"),
        Course(name = "Python-pasp")
    )

    /** Внутренний поток состояния онбординга */
    private val _state = MutableStateFlow(OnboardingState(courses = courses))

    /** Публичный поток состояния для подписки UI */
    val state = _state.asStateFlow()

    /**
     * Обрабатывает действие продолжения онбординга.
     *
     * Сохраняет факт прохождения онбординга, возвращается назад и переходит на экран входа.
     */
    fun toContinue() {
        saveVisitingOnboarding()
        navigator.back()
        navigator.navigateTo(Screen.SignIn)
    }

    /**
     * Сохраняет в настройках информацию о том, что онбординг был показан пользователю.
     */
    private fun saveVisitingOnboarding() {
        settingsManager.save { it.copy(isOnboardingShowed = true) }
    }
}
