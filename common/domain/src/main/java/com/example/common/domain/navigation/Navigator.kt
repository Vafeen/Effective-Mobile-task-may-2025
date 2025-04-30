package com.example.common.domain.navigation

interface Navigator {
    fun navigateTo(screen: Screen)
    fun back()
}