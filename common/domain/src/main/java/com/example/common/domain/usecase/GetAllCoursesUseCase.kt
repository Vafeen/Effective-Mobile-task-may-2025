package com.example.common.domain.usecase

import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesNetworkRepository
import com.example.common.domain.network.ResponseResult

class GetAllCoursesUseCase(private val coursesNetworkRepository: CoursesNetworkRepository) {
    suspend operator fun invoke(): ResponseResult<List<Course>> =
        coursesNetworkRepository.getAllCourses()
}