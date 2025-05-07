package com.example.common.domain.usecase

import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesNetworkRepository
import com.example.common.domain.network.ResponseResult

class FetchDataAndUpdateDbUseCase(
    private val networkRepository: CoursesNetworkRepository,
    private val localRepository: CoursesLocalRepository
) {
    suspend operator fun invoke(): ResponseResult<Any> =
        when (val result = networkRepository.getAllCourses()) {
            is ResponseResult.Error -> result
            is ResponseResult.Success<List<Course>> -> {
                localRepository.insert(*result.data.toTypedArray())
                ResponseResult.Success(result.data)
            }
        }
}