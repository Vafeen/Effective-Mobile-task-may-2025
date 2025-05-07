package com.example.common.domain.usecase

import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course

class InsertCoursesInLocalDatabaseUseCase(private val localRepository: CoursesLocalRepository) {
    suspend operator fun invoke(courses: List<Course>) = localRepository.insert(courses)
}