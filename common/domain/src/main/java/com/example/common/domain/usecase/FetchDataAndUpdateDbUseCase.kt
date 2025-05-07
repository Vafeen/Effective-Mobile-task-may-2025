package com.example.common.domain.usecase

import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesNetworkRepository
import com.example.common.domain.network.ResponseResult
import kotlinx.coroutines.flow.first

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

    private suspend fun cleverUpdatingDb(newCourses: List<Course>) {
        val resultCourses = mutableListOf<Course>()
        val lastCourses = localRepository.getAll().first()
        newCourses.forEach { course ->

        }
    }
}