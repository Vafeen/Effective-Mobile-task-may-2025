package com.example.common.domain.services

import com.example.common.domain.models.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsManager {
    val settingsFlow: StateFlow<Settings>
    fun save(saving: (Settings) -> Settings)
}