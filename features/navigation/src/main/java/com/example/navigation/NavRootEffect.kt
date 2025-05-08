package com.example.navigation

import androidx.navigation.NavHostController

/**
 * Эффекты навигации в корневом навигационном графе.
 */
internal sealed class NavRootEffect {

    /**
     * Эффект навигации к определённому экрану.
     *
     * @property navigate лямбда-функция, принимающая [NavHostController] и выполняющая переход.
     */
    data class NavigateToScreen(val navigate: (NavHostController) -> Unit) : NavRootEffect()

    /**
     * Эффект возврата назад в навигации.
     */
    data object Back : NavRootEffect()
}
