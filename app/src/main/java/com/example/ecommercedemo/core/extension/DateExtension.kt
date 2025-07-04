package com.example.ecommercedemo.core.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatTime(pattern: String = "HH:mm"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}
