package com.example.common.android.ui

import androidx.compose.ui.graphics.Color

/**
 * Цветовую схема для интерфейса приложения.
 *
 * @property mainColor основной цвет интерфейса.
 * @property background цвет фона.
 * @property text цвет текста.
 * @property cardColor цвет карточек.
 * @property bottomBarIndicatorColor цвет индикатора нижней панели.
 */
data class CustomColorScheme(
    val mainColor: Color,
    val background: Color,
    val text: Color,
    val cardColor: Color,
    val bottomBarIndicatorColor: Color,
)
