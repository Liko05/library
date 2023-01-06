package com.library.dto.book

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID;

data class BookInfo(
    val id: UUID,
    val title: String,
    val author: String,
    val authorId: UUID,
    val releasedAt: ZonedDateTime
)
