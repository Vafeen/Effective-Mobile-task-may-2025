package com.example.common.domain.usecase

import com.example.common.domain.local_database.CoursesLocalRepository

class GetAllCoursesFromLocalDatabaseUseCase(private val localRepository: CoursesLocalRepository) {
    operator fun invoke() = localRepository.getAll()
}