package com.example.common.data.converters

import com.example.common.data.data_models.courses_service.CourseDto
import com.example.common.domain.models.Course

internal fun CourseDto.toCourse() =
    Course(id, title, text, price, rate, startDate, hasLike, publishDate)

internal fun List<CourseDto>.toCourse() = map { it.toCourse() }