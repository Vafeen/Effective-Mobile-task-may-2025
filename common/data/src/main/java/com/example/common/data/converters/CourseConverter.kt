package com.example.common.data.converters

import com.example.common.data.data_models.courses_service.CourseDto
import com.example.common.data.local_database.entity.CourseEntity
import com.example.common.domain.models.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun CourseDto.dtoToModel() =
    Course(id, title, text, price, rate, startDate, hasLike, publishDate)

internal fun List<CourseDto>.dtoToModel() = this.map { it.dtoToModel() }
internal fun CourseEntity.entityToModel() =
    Course(id, title, text, price, rate, startDate, hasLike, publishDate)

internal fun List<CourseEntity>.entityToModel() = map { it.entityToModel() }
internal fun Flow<List<CourseEntity>>.toCourseFlow() = this.map { it.entityToModel() }
internal fun Course.toEntity() =
    CourseEntity(id, title, text, price, rate, startDate, hasLike, publishDate)