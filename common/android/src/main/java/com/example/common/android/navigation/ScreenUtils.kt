package com.example.common.android.navigation

import androidx.navigation.NavBackStackEntry
import com.example.common.domain.navigation.Screen

fun getScreenFromRoute(navBackStackEntry: NavBackStackEntry): Screen? {
    val route = navBackStackEntry.destination.route ?: return null
    return when {
        route == Screen.Onboarding::class.qualifiedName -> Screen.Onboarding
        route == Screen.SignIn::class.qualifiedName -> Screen.SignIn
        route == Screen.Main::class.qualifiedName -> Screen.Main
        route == Screen.Favourites::class.qualifiedName -> Screen.Favourites
        route == Screen.Account::class.qualifiedName -> Screen.Account
        route == Screen.BottomBarScreens::class.qualifiedName -> Screen.BottomBarScreens
        route.startsWith("${Screen.SignIn::class.qualifiedName}") -> Screen.SignIn
        else -> null
    }
}
