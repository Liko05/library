package com.library.service.impl

import com.library.client.AuthorsClient
import com.library.dto.author.MockAuthorDetailed
import com.library.dto.book.BookInfo
import com.library.dto.book.CreateBook
import com.library.mapper.BookMapper
import com.library.model.Book
import com.library.repository.BookRepository
import com.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BookServiceImpl @Autowired constructor (
    private val bookRepository: BookRepository,
    private val authorsClient: AuthorsClient,
    private val bookMapper: BookMapper

    ) : BookService{

    override fun addBook(book: CreateBook): ResponseEntity<Book> {
        val author: MockAuthorDetailed
        try {
            author = authorsClient.getAuthor(book.authorId)
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
        val newBook = Book(
            id = UUID.randomUUID(),
            title = book.title,
            author = "${author.firstName} ${author.middleName?.let { "$it " } ?: ""}${author.lastName}",
            authorId = book.authorId,
            releasedAt = book.releasedAt,
            createdAt = LocalDateTime.now(),
            description = book.description
        )
        return ResponseEntity.of(Optional.of(bookRepository.addBook(newBook)));
    }

    override fun getBook(id: UUID): ResponseEntity<Book> {
        val book: Book? = bookRepository.getBookById(id);
        return ResponseEntity.of(Optional.ofNullable(book));
    }

    //http://localhost:8080/books?page=0&size=10&sort=title&order=desc
    override fun getAllBooks(pageable: Pageable): ResponseEntity<Page<BookInfo>> {
        val books = bookRepository.getAllBooks(pageable);
        return ResponseEntity.ok(PageImpl(books.map { bookMapper.mapBookToBookInfo(it) }, pageable, books.size.toLong()));
    }
}