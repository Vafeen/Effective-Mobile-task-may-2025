package com.example.navigation

import com.example.common.domain.navigation.Screen

/**
 * Класс, представляющий состояние навигации в корневом навигационном графе.
 *
 * @property isBottomBarVisible флаг, указывающий, видна ли нижняя панель навигации.
 * @property startDestination стартовый экран для навигации.
 * @property currentScreen текущий отображаемый экран.
 */
internal data class NavRootState(
    val isBottomBarVisible: Boolean = false,
    val startDestination: Screen? = null,
    val currentScreen: Screen? = startDestination,
)
