package com.example.onboarding

/**
 * Класс, представляющий курс для отображения на экране онбординга.
 *
 * @property name название курса.
 * @property degreesIncline угол наклона элемента курса для визуального эффекта
 */
internal data class Course(
    val name: String,
    val degreesIncline: Float = 0f,
)
