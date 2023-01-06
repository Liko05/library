package com.library.dto.book

import java.time.ZonedDateTime
import java.util.UUID

data class CreateBook(
    val title: String,
    val authorId: UUID,
    val description: String?,
    val releasedAt: ZonedDateTime
)
