package com.example.common.data.services

import android.content.SharedPreferences
import android.util.Log
import com.example.common.domain.models.Settings
import com.example.common.domain.services.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json

/**
 * Реализация интерфейса [SettingsManager] для управления настройками приложения с использованием [SharedPreferences].
 * Этот класс предоставляет реактивный способ наблюдения за изменениями настроек через [StateFlow].
 *
 * @property sharedPreferences Экземпляр [SharedPreferences], используемый для хранения и загрузки настроек.
 */
internal class SettingsManagerImpl(private val sharedPreferences: SharedPreferences) :
    SettingsManager {

    private val SETTINGS_KEY = "SETTINGS_KEY"

    /**
     * Текущие настройки, загруженные из SharedPreferences.
     */
    private var settings = sharedPreferences.getSettingsOrCreateIfNull()

    /**
     * Внутренний StateFlow для отслеживания изменений настроек.
     */
    private val _settingsFlow = MutableStateFlow(settings)

    /**
     * Публичный [StateFlow] для подписки на изменения настроек.
     * С помощью этого потока можно получать уведомления об обновлениях настроек.
     */
    override val settingsFlow: StateFlow<Settings> = _settingsFlow.asStateFlow()

    /**
     * Регистрация слушателя для отслеживания изменений в [SharedPreferences].
     * При обнаружении изменения обновленные настройки отправляются в [StateFlow].
     */
    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            _settingsFlow.value = sharedPreferences.getSettingsOrCreateIfNull()
        }
    }

    /**
     * Сохраняет настройки, применяя функцию преобразования к текущим настройкам.
     * Обновленные настройки сохраняются в [SharedPreferences] и автоматически передаются
     * подписчикам через [StateFlow].
     *
     * @param saving Функция, которая принимает текущие настройки и возвращает обновленные.
     */
    @Synchronized
    override fun save(saving: (Settings) -> Settings) {
        // Обновляем настройки в памяти
        settings = saving(settings)
        Log.d("sp", "save ${Json.encodeToString(settings)}")
        // Сохраняем обновленные настройки в SharedPreferences
        sharedPreferences.save(settings)
        // Обновляем flow
        // Обновление здесь идет, потому что callback, который регистрируется выше, не всегда срабатывает.
        _settingsFlow.value = settings
    }

    /**
     * Сохраняет настройки в SharedPreferences.
     *
     * @param settings Объект [Settings], который нужно сохранить.
     */
    private fun SharedPreferences.save(settings: Settings) = saveInOrRemoveFromSharedPreferences {
        putString(SETTINGS_KEY, settings.toJsonString())
    }

    /**
     * Получает настройки из SharedPreferences или создает новые, если их нет.
     * Эта функция проверяет наличие настроек в SharedPreferences и возвращает их.
     * Если настройки не найдены, создаются новые и сохраняются в SharedPreferences.
     *
     * @return Объект [Settings], полученный из SharedPreferences или созданный по умолчанию.
     */
    private fun SharedPreferences.getSettingsOrCreateIfNull(): Settings {
        val settings = getFromSharedPreferences {
            getString(SETTINGS_KEY, null)?.let {
                Json.decodeFromString<Settings>(it)
            }
        }
        return if (settings != null) settings
        else {
            val newSettings = Settings() // Создание новых настроек по умолчанию.
            saveInOrRemoveFromSharedPreferences {
                putString(SETTINGS_KEY, newSettings.toJsonString())
            }
            newSettings
        }
    }

    /**
     * Выполняет сохранение данных в [SharedPreferences.Editor].
     * Позволяет выполнить блок сохранения и применить изменения.
     *
     * @receiver Экземпляр [SharedPreferences], в котором будет создан редактор.
     * @param save Лямбда с действиями для редактора [SharedPreferences.Editor].
     */
    private fun SharedPreferences.saveInOrRemoveFromSharedPreferences(save: SharedPreferences.Editor.() -> Unit) {
        edit().apply {
            save()
            apply()
        }
    }

    /**
     * Выполняет получение данных из [SharedPreferences].
     *
     * @receiver Экземпляр [SharedPreferences], из которого происходит чтение.
     * @param get Лямбда с действиями для получения данных из [SharedPreferences].
     * @return Результат выполнения лямбды.
     */
    private fun <T> SharedPreferences.getFromSharedPreferences(get: SharedPreferences.() -> T): T =
        get()

    /**
     * Сериализует объект [Settings] в JSON-строку.
     *
     * @receiver Объект [Settings], который нужно сериализовать.
     * @return JSON-представление объекта в виде строки.
     */
    private fun Settings.toJsonString() = Json.encodeToString(this)
}
