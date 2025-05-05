package com.example.common.android.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.android.R
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor
import com.example.common.domain.navigation.Screen

private typealias BottomBarNavigate = (() -> Unit)?

@Composable
fun BottomBar(
    currentScreen: Screen,
    navigateToMain: BottomBarNavigate,
    navigateToFavourites: BottomBarNavigate,
    navigateToAccount: BottomBarNavigate,
) {
    val colors =
        NavigationBarItemDefaults.colors(
            indicatorColor = CustomTheme.colors.bottomBarIndicatorColor,
            unselectedIconColor = CustomTheme.colors.text,
            unselectedTextColor = CustomTheme.colors.text,
            selectedIconColor = ThemeColor.green,
            selectedTextColor = ThemeColor.green
        )
    BottomAppBar(
        containerColor = CustomTheme.colors.cardColor,
        actions = {
            NavigationBarItem(
                selected = currentScreen == Screen.Main, icon = {
                    Icon(
                        painter = painterResource(R.drawable.home),
                        contentDescription = stringResource(R.string.home_screen)
                    )
                }, onClick = navigateToMain ?: {}, colors = colors,
                label = { Text(stringResource(R.string.home_screen)) })
            NavigationBarItem(
                selected = currentScreen == Screen.Favourites, icon = {
                    Icon(
                        painter = painterResource(R.drawable.favourites),
                        contentDescription = stringResource(R.string.favourites_screen)
                    )
                }, onClick = navigateToFavourites ?: {}, colors = colors,
                label = { Text(stringResource(R.string.favourites_screen)) })
            NavigationBarItem(
                selected = currentScreen == Screen.Account, icon = {
                    Icon(
                        painter = painterResource(R.drawable.account),
                        contentDescription = stringResource(R.string.account_screen)
                    )
                }, onClick = navigateToAccount ?: {}, colors = colors,
                label = { Text(stringResource(R.string.account_screen)) })
        })
}