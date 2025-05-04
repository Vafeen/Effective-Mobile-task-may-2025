package com.example.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.common.domain.navigation.Screen
import com.example.onboarding.OnboardingScreen
import com.example.sign_in.SignInScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavRoot() {
    val viewModel = koinViewModel<NavRootViewModel>()
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
        val tween = tween<Float>(durationMillis = 0)
        NavHost(
            navController = navController, startDestination = startDestination,
            enterTransition = { fadeIn(animationSpec = tween) },
            exitTransition = { fadeOut(animationSpec = tween) },
            popEnterTransition = { fadeIn(animationSpec = tween) },
            popExitTransition = { fadeOut(animationSpec = tween) },
        ) {
            composable<Screen.Onboarding> { OnboardingScreen(viewModel) }
            composable<Screen.SignIn> { SignInScreen(viewModel) }
            navigation<Screen.BottomBarScreens>(startDestination = Screen.Main) {
                composable<Screen.Main> { }
                composable<Screen.Favourites> { }
                composable<Screen.Account> { }
            }
        }
    }
}