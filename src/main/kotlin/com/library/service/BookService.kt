package com.library.service

import com.library.dto.book.BookInfo
import com.library.dto.book.CreateBook
import com.library.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import java.util.UUID


interface BookService {
    fun addBook(book: CreateBook): ResponseEntity<Book>

    fun getBook(id: UUID): ResponseEntity<Book>

    fun getAllBooks(pageable: Pageable): ResponseEntity<Page<BookInfo>>
}