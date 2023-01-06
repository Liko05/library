package com.library.repository

import com.library.model.Book
import org.springframework.data.domain.Pageable
import java.util.UUID

interface BookRepository {
    fun addBook(book: Book): Book
    fun getBookById(id: UUID): Book?
    fun getAllBooks(pageable: Pageable): List<Book>
    fun getBooksByAuthor(id: UUID): List<Book>
}