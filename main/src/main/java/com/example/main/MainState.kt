package com.example.main

import com.example.common.domain.models.Course

internal data class MainState(
    val courses: List<Course> = listOf(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val searchRequest: String = "",
    val sortType: String,
)