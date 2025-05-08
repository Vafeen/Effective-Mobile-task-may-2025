package com.example.common.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val basePalette = CustomColorScheme(
        mainColor = Color(0xFF24252A),
        background = Color.White,
        text = Color.Black,
        cardColor = Color(0xFFCECFD3),
        bottomBarIndicatorColor = Color(0xFF32333A)
    )

    val baseDarkPalette = basePalette.copy(
        background = Color.Black,
        text = Color.White,
        cardColor = Color(0xFF24252A)
    )

    val colors = if (!darkTheme) {
        basePalette
    } else {
        baseDarkPalette
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}


/**
 * Объект для доступа к текущей пользовательской цветовой схеме темы.
 */
object CustomTheme {
    /**
     * Текущая пользовательская цветовая схема.
     */
    val colors: CustomColorScheme
        @Composable
        get() = LocalColors.current
}

private val LocalColors = staticCompositionLocalOf<CustomColorScheme> {
    error("Composition error")
}