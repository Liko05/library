package com.library.controller

import com.library.dto.book.BookInfo
import com.library.dto.book.CreateBook
import com.library.model.Book
import com.library.repository.BookRepository
import com.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/books")
class BookController @Autowired constructor (
    private val bookService: BookService
    ){
    @PostMapping("")
    fun addBook(@RequestBody book : CreateBook): ResponseEntity<Book> {
        return bookService.addBook(book);
    }

    @RequestMapping("/get/{id}")
    fun getBook(@PathVariable id: UUID): ResponseEntity<Book> {
        return bookService.getBook(id);
    }

    @GetMapping("")
    fun getAllBooks(@PageableDefault(page = 0, size = 20) pageable : Pageable): ResponseEntity<Page<BookInfo>> {
        return bookService.getAllBooks(pageable);
    }
}