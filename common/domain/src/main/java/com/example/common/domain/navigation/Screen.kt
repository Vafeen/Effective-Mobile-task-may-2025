package com.example.common.domain.navigation

sealed class Screen {
    data object Onboarding : Screen()
    data object SignUp : Screen()
    data object Main : Screen()
    data object Favourites : Screen()
    data object Account : Screen()
    data object BottomBarScreens : Screen()
}