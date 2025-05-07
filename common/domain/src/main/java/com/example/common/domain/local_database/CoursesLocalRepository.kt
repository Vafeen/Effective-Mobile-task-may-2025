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
     * @return [Flow] с текущим списком курсов, обновляющийся при изменениях.
     */
    fun getAll(): Flow<List<Course>>

    /**
     * Вставляет один или несколько курсов в локальное хранилище.
     *
     * @param course Один или несколько объектов [Course] для вставки.
     */
    suspend fun insert(vararg course: Course)

    /**
     * Удаляет один или несколько курсов из локального хранилища.
     *
     * @param course Один или несколько объектов [Course] для удаления.
     */
    suspend fun delete(vararg course: Course)
}
