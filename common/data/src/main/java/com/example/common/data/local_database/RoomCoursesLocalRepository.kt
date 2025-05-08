package com.example.common.data.local_database

import com.example.common.data.converters.toCourseFlow
import com.example.common.data.converters.toEntity
import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

/**
 * Реализация [CoursesLocalRepository] для работы с локальной базой данных курсов с использованием Room.
 *
 * Отвечает за получение, вставку и удаление данных курсов в локальной базе.
 *
 * @property courseDao DAO для доступа к таблице курсов в базе данных.
 * @see CoursesLocalRepository интерфейс, который реализует данный класс
 */
internal class RoomCoursesLocalRepository(db: AppDatabase) : CoursesLocalRepository {

    /** DAO для работы с курсами в базе данных */
    private val courseDao = db.courseDao()

    /**
     * Получает поток со списком всех курсов из локальной базы данных.
     *
     * @return [Flow] со списком доменных моделей [Course].
     *
     * Логика работы:
     * 1. Запрашивает все курсы из базы через DAO.
     * 2. Преобразует полученные сущности в доменные модели с помощью расширения [toCourseFlow].
     */
    override fun getAll(): Flow<List<Course>> = courseDao.getAll().toCourseFlow()

    /**
     * Вставляет список курсов в локальную базу данных.
     *
     * @param courses список доменных моделей [Course] для вставки.
     *
     * Логика работы:
     * 1. Преобразует каждую доменную модель в сущность базы данных с помощью [toEntity].
     * 2. Вставляет каждую сущность через DAO.
     */
    override suspend fun insert(courses: List<Course>) {
        courses.forEach { courseDao.insert(it.toEntity()) }
    }

    /**
     * Удаляет список курсов из локальной базы данных.
     *
     * @param course список доменных моделей [Course] для удаления.
     *
     * Логика работы:
     * 1. Преобразует каждую доменную модель в сущность базы данных с помощью [toEntity].
     * 2. Удаляет каждую сущность через DAO.
     */
    override suspend fun delete(course: List<Course>) {
        course.forEach { courseDao.delete(it.toEntity()) }
    }
}