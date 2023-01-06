package com.library.mapper

import com.library.dto.book.BookInfo
import com.library.model.Book
import org.springframework.stereotype.Component

@Component
class BookMapper {
    fun mapBookToBookInfo(book: Book) : BookInfo {
        return BookInfo(
            id = book.id,
            title = book.title,
            author = book.author,
            authorId = book.authorId,
            releasedAt = book.releasedAt
        )
    }
}