package com.example.common.domain.utils

import com.example.common.domain.models.Course
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlin.test.Test

internal class CleverEqualsTest {

    private val baseCourse = Course(
        id = 1,
        title = "Kotlin Basics",
        text = "Learn Kotlin programming",
        price = "$99",
        rate = "4.8",
        startDate = "2023-01-01",
        hasLike = false,
        publishDate = "2022-12-01"
    )

    @Test
    fun `courses with same properties except hasLike should be equal`() {
        val course1 = baseCourse.copy(hasLike = false)
        val course2 = baseCourse.copy(hasLike = true)

        assertTrue(course1 cleverEquals course2)
        assertTrue(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different ids should not be equal`() {
        val course1 = baseCourse.copy(id = 1)
        val course2 = baseCourse.copy(id = 2)

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different titles should not be equal`() {
        val course1 = baseCourse.copy(title = "Kotlin Basics")
        val course2 = baseCourse.copy(title = "Advanced Kotlin")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different texts should not be equal`() {
        val course1 = baseCourse.copy(text = "Learn Kotlin")
        val course2 = baseCourse.copy(text = "Master Kotlin")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different prices should not be equal`() {
        val course1 = baseCourse.copy(price = "$99")
        val course2 = baseCourse.copy(price = "$199")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different rates should not be equal`() {
        val course1 = baseCourse.copy(rate = "4.8")
        val course2 = baseCourse.copy(rate = "4.9")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different start dates should not be equal`() {
        val course1 = baseCourse.copy(startDate = "2023-01-01")
        val course2 = baseCourse.copy(startDate = "2023-02-01")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `courses with different publish dates should not be equal`() {
        val course1 = baseCourse.copy(publishDate = "2022-12-01")
        val course2 = baseCourse.copy(publishDate = "2022-11-01")

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

    @Test
    fun `course should equal itself`() {
        assertTrue(baseCourse cleverEquals baseCourse)
    }

    @Test
    fun `completely different courses should not be equal`() {
        val course1 = baseCourse
        val course2 = baseCourse.copy(
            id = 2,
            title = "Different",
            text = "Different",
            price = "$0",
            rate = "0.0",
            startDate = "2000-01-01",
            publishDate = "2000-01-01"
        )

        assertFalse(course1 cleverEquals course2)
        assertFalse(course2 cleverEquals course1)
    }

}