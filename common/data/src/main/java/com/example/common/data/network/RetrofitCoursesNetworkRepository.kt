package com.example.common.data.network

import com.example.common.data.converters.dtoToModel
import com.example.common.data.data_models.courses_service.CourseDto
import com.example.common.data.services.courses_service.RetrofitCoursesService
import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesNetworkRepository
import com.example.common.domain.network.ResponseResult

/**
 * Реализация [CoursesNetworkRepository] для работы с данными курсов через Retrofit.
 *
 * Преобразует данные из сетевого слоя ([CourseDto]) в доменные модели ([Course]).
 * Обрабатывает все возможные ошибки сети и преобразования данных.
 *
 * @property retrofitCoursesService сервис для выполнения сетевых запросов к API курсов
 * @see CoursesNetworkRepository интерфейс, который реализует данный класс
 * @see ResponseResult обертка для обработки успешных и ошибочных результатов
 */
internal class RetrofitCoursesNetworkRepository(
    /** Retrofit-сервис для выполнения запросов к API курсов */
    private val retrofitCoursesService: RetrofitCoursesService
) : CoursesNetworkRepository {

    /**
     * Получает список всех курсов из сети.
     *
     * @return [ResponseResult] с одним из вариантов:
     * - [ResponseResult.Success] со списком доменных моделей [Course] при успешном запросе
     * - [ResponseResult.Error] с соответствующим исключением при возникновении ошибки
     *
     * @throws Exception если произошла непредвиденная ошибка (не перехваченная [getResponseResultWrappedAllErrors])
     *
     * Логика работы:
     * 1. Выполняет сетевой запрос через [retrofitCoursesService]
     * 2. Преобразует DTO-модели ([CourseDto]) в доменные модели ([Course])
     * 3. Обрабатывает все возможные ошибки через [getResponseResultWrappedAllErrors]
     */
    override suspend fun getAllCourses(): ResponseResult<List<Course>> =
        getResponseResultWrappedAllErrors {
            ResponseResult.Success(
                (retrofitCoursesService.getAll().body()?.courses as List<CourseDto>).dtoToModel()
            )
        }
}