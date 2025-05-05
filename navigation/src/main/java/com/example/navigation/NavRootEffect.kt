package com.example.navigation

import androidx.navigation.NavHostController

internal sealed class NavRootEffect {
    data class NavigateToScreen(val navigate: (NavHostController) -> Unit) : NavRootEffect()
    data object Back : NavRootEffect()
}