package com.example.common.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.common.data.local_database.dao.CourseDao
import com.example.common.data.local_database.entity.CourseEntity

@Database(entities = [CourseEntity::class], version = 1, exportSchema = true)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao

    companion object {
        const val NAME = "EM-Task-AppDatabase"
    }
}