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

/**
 * ViewModel для главного экрана приложения
 *
 * @property editableDispatchers диспетчеры корутин, используемые для управления потоками выполнения.
 * @property fetchDataAndUpdateDbUseCase юзкейс для загрузки данных из сети и обновления локальной базы.
 * @property getAllCoursesFromLocalDatabaseUseCase юзкейс для получения всех курсов из локальной базы.
 * @property insertCoursesInLocalDatabaseUseCase юзкейс для вставки курсов в локальную базу.
 * @property context контекст приложения для доступа к ресурсам (строкам).
 */
internal class MainViewModel(
    private val editableDispatchers: EditableDispatchers,
    private val fetchDataAndUpdateDbUseCase: FetchDataAndUpdateDbUseCase,
    private val getAllCoursesFromLocalDatabaseUseCase: GetAllCoursesFromLocalDatabaseUseCase,
    private val insertCoursesInLocalDatabaseUseCase: InsertCoursesInLocalDatabaseUseCase,
    context: Application
) : ViewModel() {

    /** Внутренний поток состояния экрана */
    private val _state = MutableStateFlow(
        MainState(
            sortType = context.getString(R.string.sort_by_date)
        )
    )

    /** Публичный поток состояния для подписки UI */
    val state = _state.asStateFlow()

    /** Строка сортировки по дате из ресурсов */
    private val sortByDateStr = context.getString(R.string.sort_by_date)

    /** Строка сортировки по идентификатору из ресурсов */
    private val sortByIdStr = context.getString(R.string.sort_by_id)

    /** Строка ошибки из ресурсов */
    private val error = context.getString(R.string.error)

    init {
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            getAllCoursesFromLocalDatabaseUseCase.invoke().collect { courses ->
                _state.update { it.copy(courses = courses) }
            }
        }
        fetchData()
    }

    /**
     * Загружает данные из сети и обновляет локальную базу данных.
     *
     * Обновляет состояние экрана с индикатором загрузки и ошибками.
     */
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
                    delay(1000) // задержка для видимости загрузки при ошибке сети
                    it.copy(
                        error = error,
                        isLoading = false
                    )
                }
            }
        }
    }

    /**
     * Обновляет поисковый запрос в состоянии.
     *
     * @param searchRequest новая строка поискового запроса.
     */
    fun updateSearchRequest(searchRequest: String) {
        _state.update { it.copy(searchRequest = searchRequest) }
    }

    /**
     * Переключает сортировку списка курсов между сортировкой по дате публикации и по идентификатору.
     *
     * Сортировка выполняется асинхронно в io-диспетчере.
     */
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

    /**
     * Обновляет состояние избранного для конкретного курса.
     *
     * Инвертирует значение [hasLike] и сохраняет изменения в локальной базе.
     *
     * @param course курс, для которого обновляется статус избранного.
     */
    fun updateFavourites(course: Course) {
        viewModelScope.launch(editableDispatchers.ioDispatcher) {
            insertCoursesInLocalDatabaseUseCase.invoke(listOf(course.copy(hasLike = !course.hasLike)))
        }
    }
}
