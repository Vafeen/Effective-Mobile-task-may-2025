package com.example.common.data.local_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
