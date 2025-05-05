package com.example.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.common.android.components.BottomBar
import com.example.common.android.navigation.getScreenFromRoute
import com.example.common.android.ui.CustomTheme
import com.example.common.domain.navigation.Screen
import com.example.onboarding.OnboardingScreen
import com.example.sign_in.SignInScreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("RestrictedApi")
@Composable
fun NavRoot() {
    val viewModel = koinViewModel<NavRootViewModel>()
    val navController = rememberNavController()

    val state by viewModel.state.collectAsState()
    LaunchedEffect(null) {
        navController.currentBackStackEntryFlow.collect {
            val currentScreen = getScreenFromRoute(it)
                ?: return@collect
            viewModel.updateCurrentScreen(currentScreen)
        }
    }
    
    LaunchedEffect(null) {
        viewModel.effects.collect { effect ->
            when (effect) {
                NavRootEffect.Back -> navController.popBackStack()
                is NavRootEffect.NavigateToScreen -> effect.navigate(navController)
            }
        }
    }

    Scaffold(
        containerColor = CustomTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (state.isBottomBarVisible) {
                state.currentScreen?.let {
                    BottomBar(
                        currentScreen = it,
                        navigateToMain = { viewModel.navigateTo(Screen.Main) },
                        navigateToFavourites = { viewModel.navigateTo(Screen.Favourites) },
                        navigateToAccount = { viewModel.navigateTo(Screen.Account) })
                }
            }
        }
    ) {
        val x = it
        state.startDestination?.let { startDestination ->
            val tween = tween<Float>(durationMillis = 0)
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navController = navController, startDestination = startDestination,
                enterTransition = { fadeIn(animationSpec = tween) },
                exitTransition = { fadeOut(animationSpec = tween) },
                popEnterTransition = { fadeIn(animationSpec = tween) },
                popExitTransition = { fadeOut(animationSpec = tween) },
            ) {
                composable<Screen.Onboarding> { OnboardingScreen(viewModel) }
                composable<Screen.SignIn> { SignInScreen(viewModel) }
                navigation<Screen.BottomBarScreens>(startDestination = Screen.Main) {
                    composable<Screen.Main> { Text("Main") }
                    composable<Screen.Favourites> { Text("Favourites") }
                    composable<Screen.Account> { Text("Account") }
                }
            }
        }
    }
}