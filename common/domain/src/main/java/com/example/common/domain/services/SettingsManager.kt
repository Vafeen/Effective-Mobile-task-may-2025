package com.example.common.domain.services

import com.example.common.domain.models.Settings
import kotlinx.coroutines.flow.StateFlow

/**
 * Этот класс предоставляет реактивный способ наблюдения за изменениями настроек через [StateFlow
 */
interface SettingsManager {
    val settingsFlow: StateFlow<Settings>
    fun save(saving: (Settings) -> Settings)
}