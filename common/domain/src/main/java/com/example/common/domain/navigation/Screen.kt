package com.example.common.domain.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Onboarding : Screen()

    @Serializable
    data object SignIn : Screen()

    @Serializable
    data object Main : Screen()

    @Serializable
    data object Favourites : Screen()

    @Serializable
    data object Account : Screen()

    @Serializable
    data object BottomBarScreens : Screen()
}