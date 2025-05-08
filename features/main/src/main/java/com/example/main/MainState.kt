package com.example.main

import com.example.common.domain.models.Course

/**
 * Класс, представляющий состояние главного экрана приложения.
 *
 * @property courses список курсов, отображаемых на экране.
 * @property isLoading флаг, указывающий, идет ли в данный момент загрузка данных.
 * @property error сообщение об ошибке, если она произошла, иначе null.
 * @property searchRequest текущий поисковый запрос пользователя.
 * @property sortType тип сортировки, применяемый к списку курсов.
 */
internal data class MainState(
    val courses: List<Course> = listOf(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val searchRequest: String = "",
    val sortType: String,
)
