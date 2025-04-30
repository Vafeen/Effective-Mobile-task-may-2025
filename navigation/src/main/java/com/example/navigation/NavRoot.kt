package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.common.domain.navigation.Screen

@Composable
fun NavRoot() {
    val viewModel = viewModel<NavRootViewModel>()
    val navController = rememberNavController()

    val state by viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.effects.collect { effect ->
            when (effect) {
                NavRootEffect.Back -> navController.popBackStack()
                is NavRootEffect.NavigateToScreen -> navController.navigate(effect.screen)
            }
        }
    }
    state.startDestination?.let { startDestination ->
        NavHost(
            navController = navController, startDestination = startDestination
        ) {
            composable<Screen.Onboarding> { }
            composable<Screen.SignUp> { }
            navigation<Screen.BottomBarScreens>(startDestination = Screen.Main) {
                composable<Screen.Main> { }
                composable<Screen.Favourites> { }
                composable<Screen.Account> { }
            }
        }
    }
}