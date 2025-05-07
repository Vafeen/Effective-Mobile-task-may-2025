package com.example.common.domain.utils

import com.example.common.domain.models.Course

/**
 * Умное сравнение курсов без учета локального сохранения добавления в избранное,
 * чтобы не происходило перезаписи при обновлении данных
 */
infix fun Course.cleverEquals(course: Course): Boolean = course.id == id &&
        course.title == title &&
        course.text == text &&
        course.price == price &&
        course.rate == rate &&
        course.startDate == startDate &&
        course.publishDate == publishDate

/**
 * Умное обновление курсов без учета локального сохранения добавления в избранное,
 * чтобы не происходило перезаписи при обновлении данных
 */
suspend fun cleverUpdatingCourses(
    lastCourses: List<Course>,
    newCourses: List<Course>,
    insert: suspend (List<Course>) -> Unit,
    delete: suspend (List<Course>) -> Unit,
) {
    val resultCourses = mutableListOf<Course>()
    val resultForDelete = mutableListOf<Course>()
    newCourses.forEach { course ->
        // ищем, есть ли новый курс в старых (не учитывая добавление в избранное с помощью cleverEquals)
        val newInLast = lastCourses.firstOrNull { it cleverEquals course }
        // если такого нет, новый
        if (newInLast == null) {
            resultCourses.add(course)
        }
    }
    lastCourses.forEach { course ->
        // ищем, есть ли старый курс в новых данных (не учитывая добавление в избранное с помощью cleverEquals)
        val lastInNew = newCourses.firstOrNull { it cleverEquals course }
        // если старого курса в новых нет, то его нужно удалить
        if (lastInNew == null) {
            resultForDelete.add(course)
        }
    }
    delete(resultForDelete)
    insert(resultCourses)
}