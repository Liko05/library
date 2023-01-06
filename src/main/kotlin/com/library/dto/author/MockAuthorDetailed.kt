package com.library.dto.author

import java.time.LocalDate
import java.util.UUID

data class MockAuthorDetailed(
    val id: UUID,
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val birth: LocalDate,
    val country: String,
    val description: String
)
