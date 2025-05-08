package com.example.common.data.data_models.courses_service

/**
 * Внутренний дата-класс, представляющий ответ со списком курсов.
 *
 * @property courses список объектов [CourseDto], содержащих информацию о курсах.
 */
internal data class CoursesResponse(
    val courses: List<CourseDto>
)