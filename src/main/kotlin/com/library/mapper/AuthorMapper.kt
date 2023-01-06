package com.library.mapper

import com.library.dto.author.AuthorBasic
import com.library.dto.author.AuthorDetailed
import com.library.dto.author.MockAuthorDetailed
import com.library.model.Book
import org.springframework.stereotype.Component

@Component
class AuthorMapper {
    fun mapMockAuthorDetailedToAuthorDetailed(mockAuthorDetailed: MockAuthorDetailed, books: List<Book>): AuthorDetailed {
        return AuthorDetailed(
            id = mockAuthorDetailed.id,
            fullName = "${mockAuthorDetailed.firstName} ${mockAuthorDetailed.middleName?.let { "$it " } ?: ""}${mockAuthorDetailed.lastName}",
            books = books,
            dateOfBirth = mockAuthorDetailed.birth,
            country = mockAuthorDetailed.country,
            description = mockAuthorDetailed.description
        )
    }

    fun mapMockAuthorDetailedToAuthorBasic(mockAuthorDetailed: MockAuthorDetailed): AuthorBasic {
        return AuthorBasic(
            id = mockAuthorDetailed.id,
            fullName = "${mockAuthorDetailed.firstName} ${mockAuthorDetailed.middleName?.let { "$it " } ?: ""}${mockAuthorDetailed.lastName}",
            country = mockAuthorDetailed.country,
            dateOfBirth = mockAuthorDetailed.birth
        )
    }
}