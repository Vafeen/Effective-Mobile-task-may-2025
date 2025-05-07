package com.example.common.domain.local_database

import com.example.common.domain.models.Course
import kotlinx.coroutines.flow.Flow
/**
 * Репозиторий для локального хранения и управления курсами.
 */
interface CoursesLocalRepository {

    /**
     * Получает поток со списком всех курсов из локального хранилища.
     *
     * @return [Flow] с текущим списком курсов, который обновляется при изменениях данных.
     */
    fun getAll(): Flow<List<Course>>

    /**
     * Вставляет список курсов в локальное хранилище.
     *
     * @param courses Список объектов [Course] для вставки.
     */
    suspend fun insert(courses: List<Course>)

    /**
     * Удаляет список курсов из локального хранилища.
     *
     * @param courses Список объектов [Course] для удаления.
     */
    suspend fun delete(courses: List<Course>)
}
