package com.example.common.data.local_database

import com.example.common.data.converters.toCourseFlow
import com.example.common.data.converters.toEntity
import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

internal class RoomCoursesLocalRepository(db: AppDatabase) : CoursesLocalRepository {
    private val courseDao = db.courseDao()

    override fun getAll(): Flow<List<Course>> = courseDao.getAll().toCourseFlow()

    override suspend fun insert(course: List<Course>) {
        course.forEach { courseDao.insert(it.toEntity()) }
    }

    override suspend fun delete(course: List<Course>) {
        course.forEach { courseDao.delete(it.toEntity()) }
    }
}