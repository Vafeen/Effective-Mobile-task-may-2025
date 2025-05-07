package com.example.navigation

import com.example.common.domain.navigation.Screen

internal data class NavRootState(
    val isBottomBarVisible: Boolean = false,
    val startDestination: Screen? = null,
    val currentScreen: Screen? = startDestination,
)