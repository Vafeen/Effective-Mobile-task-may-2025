package com.example.common.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDateToRussian(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    val date = LocalDate.parse(this, inputFormatter)
    val formatted = date.format(outputFormatter)
    val parts = formatted.split(" ")
    return if (parts.size == 3) {
        val day = parts[0]
        val month = parts[1].replaceFirstChar { it.titlecase(Locale("ru")) }
        val year = parts[2]
        "$day $month $year"
    } else {
        formatted
    }
}

fun String.parseToDate(): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()))
