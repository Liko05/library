package com.library.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID

data class Book(
    @Id
    val id: UUID,
    val title: String,
    val author: String,
    val authorId: UUID,
    val description: String?,
    val releasedAt: ZonedDateTime,
    val createdAt: LocalDateTime,
)
