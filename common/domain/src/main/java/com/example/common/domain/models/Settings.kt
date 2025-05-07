package com.example.common.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val isOnboardingShowed: Boolean = false,
    val isSignedIn: Boolean = false,
)
