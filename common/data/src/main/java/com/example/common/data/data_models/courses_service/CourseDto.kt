package com.example.common.data.data_models.courses_service

/**
 * DTO  курса.
 *
 * @property id уникальный идентификатор курса.
 * @property title название курса.
 * @property text описание курса.
 * @property price цена курса.
 * @property rate рейтинг курса.
 * @property startDate дата начала курса.
 * // @property hasLike поле, отвечающее за наличие лайка
 * @property publishDate дата публикации курса.
 */
internal data class CourseDto(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    // val hasLike: Boolean, // убрал здесь это поле, чтобы не получалось так,
    // что по тз у пользователя уже после регистрации есть курсы в избранном
    val publishDate: String
)