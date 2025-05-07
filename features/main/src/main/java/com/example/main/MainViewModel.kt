package com.example.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.R
import com.example.common.domain.coroutines.EditableDispatchers
import com.example.common.domain.models.Course
import com.example.common.domain.network.ResponseResult
import com.example.common.domain.usecase.FetchDataAndUpdateDbUseCase
import com.example.common.domain.usecase.GetAllCoursesFromLocalDatabaseUseCase
import com.example.common.domain.usecase.InsertCoursesInLocalDatabaseUseCase
import com.example.common.utils.parseToDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val editableDispatchers: EditableDispatchers,
    private val fetchDataAndUpdateDbUseCase: FetchDataAndUpdateDbUseCase,
    private val getAllCoursesFromLocalDatabaseUseCase: GetAllCoursesFromLocalDatabaseUseCase,
    private val insertCoursesInLocalDatabaseUseCase: InsertCoursesInLocalDatabaseUseCase,
    context: Application
) : ViewModel() {
    private val _state = MutableStateFlow(
        MainState(
            sortType = context.getString(R.string.sort_by_date)
        )
    )
    val state = _state.asStateFlow()
    private val sortByDateStr = context.getString(R.string.sort_by_date)
    private val sortByIdStr = context.getString(R.string.sort_by_id)
    private val error = context.getString(R.string.error)

    init {
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            getAllCoursesFromLocalDatabaseUseCase.invoke().collect { courses ->
                _state.update { it.copy(courses = courses) }
            }
        }
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = fetchDataAndUpdateDbUseCase.invoke()
            when (result) {
                is ResponseResult.Success<Any> -> {
                    _state.update {
                        it.copy(
                            error = null,
                            isLoading = false
                        )
                    }
                }

                is ResponseResult.Error -> _state.update {
                    delay(1000) // для видимости загрузки в случае ошибки сети
                    it.copy(
                        error = error,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateSearchRequest(searchRequest: String) {
        _state.update { it.copy(searchRequest = searchRequest) }
    }

    fun sortByPublishDate() {
        // список может быть большой, и синхронно это делать не стоит
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            _state.update {
                when (it.sortType) {
                    sortByDateStr -> it.copy(
                        courses = it.courses.sortedBy { course -> course.id },
                        sortType = sortByIdStr
                    )

                    sortByIdStr -> it.copy(
                        courses = it.courses.sortedBy { course -> course.publishDate.parseToDate() },
                        sortType = sortByDateStr
                    )

                    else -> it
                }
            }
        }
    }

    fun updateFavourites(course: Course) {
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            insertCoursesInLocalDatabaseUseCase.invoke(listOf(course.copy(hasLike = !course.hasLike)))
        }
    }
}