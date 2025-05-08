package com.example.onboarding

/**
 * Класс, представляющий состояние экрана онбординга.
 *
 * @property courses список курсов, отображаемых на экране онбординга.
 */
internal data class OnboardingState(
    val courses: List<Course>
)
