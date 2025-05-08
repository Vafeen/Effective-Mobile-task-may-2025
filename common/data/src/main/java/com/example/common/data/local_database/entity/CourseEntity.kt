package com.example.common.data.local_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность базы данных, представляющая курс.
 *
 * @property id уникальный идентификатор курса (первичный ключ).
 * @property title название курса.
 * @property text описание курса.
 * @property price цена курса.
 * @property rate рейтинг курса.
 * @property startDate дата начала курса.
 * @property hasLike флаг, указывающий, добавлен ли курс в избранное.
 * @property publishDate дата публикации курса.
 */
@Entity(tableName = "Course")
internal data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
