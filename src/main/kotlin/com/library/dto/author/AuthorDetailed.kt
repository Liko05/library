package com.library.dto.author

import com.library.model.Book
import java.time.LocalDate
import java.util.*

data class AuthorDetailed(
    val id: UUID,
    val fullName: String,
    val dateOfBirth: LocalDate,
    val country: String,
    val description: String,
    val books :List<Book>
)
