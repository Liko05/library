package com.library.dto.author

import java.time.LocalDate
import java.util.UUID

data class AuthorBasic(
    val id: UUID,
    val fullName: String,
    val dateOfBirth: LocalDate,
    val country: String
)
