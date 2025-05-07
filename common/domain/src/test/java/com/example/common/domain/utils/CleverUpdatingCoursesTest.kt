package com.example.common.domain.utils

import com.example.common.domain.models.Course
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CleverUpdatingCoursesTest {
    private val testCourse1 = Course(
        id = 1,
        title = "Course 1",
        text = "Description 1",
        price = "$100",
        rate = "4.5",
        startDate = "2023-01-01",
        hasLike = false,
        publishDate = "2023-01-01"
    )

    private val testCourse1Liked = testCourse1.copy(hasLike = true)
    private val testCourse2 = Course(
        id = 2,
        title = "Course 2",
        text = "Description 2",
        price = "$200",
        rate = "4.7",
        startDate = "2023-02-01",
        hasLike = false,
        publishDate = "2023-02-01"
    )
    private val testCourse3 = Course(
        id = 3,
        title = "Course 3",
        text = "Description 3",
        price = "$300",
        rate = "4.9",
        startDate = "2023-03-01",
        hasLike = false,
        publishDate = "2023-03-01"
    )

    @Test
    fun `no changes between old and new courses`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2)
        val newCourses = listOf(testCourse1, testCourse2)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertTrue(inserted.isEmpty())
        assertTrue(deleted.isEmpty())
    }

    @Test
    fun `add new course to existing ones`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2)
        val newCourses = listOf(testCourse1, testCourse2, testCourse3)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertEquals(listOf(testCourse3), inserted)
        assertTrue(deleted.isEmpty())
    }

    @Test
    fun `remove course from existing ones`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2, testCourse3)
        val newCourses = listOf(testCourse1, testCourse2)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertTrue(inserted.isEmpty())
        assertEquals(listOf(testCourse3), deleted)
    }

    @Test
    fun `like status change should not trigger insert or delete`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2)
        val newCourses = listOf(testCourse1Liked, testCourse2)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertTrue(inserted.isEmpty())
        assertTrue(deleted.isEmpty())
    }

    @Test
    fun `complete change of courses`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2)
        val newCourses = listOf(testCourse3)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertEquals(listOf(testCourse3), inserted)
        assertEquals(lastCourses, deleted)
    }

    @Test
    fun `empty last courses should insert all new courses`() = runBlocking {
        val lastCourses = emptyList<Course>()
        val newCourses = listOf(testCourse1, testCourse2)

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertEquals(newCourses, inserted)
        assertTrue(deleted.isEmpty())
    }

    @Test
    fun `empty new courses should delete all last courses`() = runBlocking {
        val lastCourses = listOf(testCourse1, testCourse2)
        val newCourses = emptyList<Course>()

        val inserted = mutableListOf<Course>()
        val deleted = mutableListOf<Course>()

        cleverUpdatingCourses(
            lastCourses, newCourses,
            insert = { inserted.addAll(it) },
            delete = { deleted.addAll(it) }
        )

        assertTrue(inserted.isEmpty())
        assertEquals(lastCourses, deleted)
    }
}