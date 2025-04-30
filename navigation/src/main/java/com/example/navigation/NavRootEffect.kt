package com.example.navigation

import com.example.common.domain.navigation.Screen

internal sealed class NavRootEffect {
    data class NavigateToScreen(val screen: Screen) : NavRootEffect()
    data object Back : NavRootEffect()
}