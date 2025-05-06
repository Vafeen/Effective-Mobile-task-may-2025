package com.example.main

import androidx.compose.runtime.Composable
import com.example.common.domain.navigation.Navigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(navigator: Navigator) {
    val viewModel: MainViewModel = koinViewModel { parametersOf(navigator) }

}