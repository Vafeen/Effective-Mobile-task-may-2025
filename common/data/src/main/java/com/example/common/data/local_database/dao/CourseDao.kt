package com.example.common.data.local_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.data.local_database.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CourseDao {
    @Query("SELECT * FROM course")
    fun getAll(): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courseEntity: CourseEntity)

    @Delete
    suspend fun delete(courseEntity: CourseEntity)
}