package com.example.common.domain.usecase

import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesRepository
import com.example.common.domain.network.ResponseResult

class GetAllCoursesUseCase(private val coursesRepository: CoursesRepository) {
    suspend operator fun invoke(): ResponseResult<List<Course>> = coursesRepository.getAllCourses()
}