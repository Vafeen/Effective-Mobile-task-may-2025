package com.example.common.domain.network

import com.example.common.domain.models.Course

/**
 * Репозиторий для работы с данными о курсах.
 * Предоставляет методы для получения списка курсов из удаленного источника данных.
 *
 * Использует [ResponseResult] для обработки успешных и ошибочных состояний.
 */
interface CoursesRepository {
    /**
     * Получает список всех доступных курсов.
     *
     * @return [ResponseResult], содержащий:
     * - [ResponseResult.Success] со списком [Course] в случае успешного получения данных
     * - [ResponseResult.Error] в случае возникновения ошибки при запросе
     */
    suspend fun getAllCourses(): ResponseResult<List<Course>>
}