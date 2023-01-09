package com.library.repository.impl

import com.library.model.Book
import com.library.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BookRepositoryImpl : BookRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun addBook(book: Book): Book {
        mongoTemplate.save(book)
        return book
    }

    override fun getBookById(id: UUID): Book? {
        return mongoTemplate.findById(id, Book::class.java)
    }

    override fun getAllBooks(pageable: Pageable): List<Book> {
        val query = Query().with(pageable)
        return mongoTemplate.find(query, Book::class.java)
    }

    override fun getBooksByAuthor(id: UUID): List<Book> {
        val query = Query().addCriteria(Criteria.where("authorId").isEqualTo(id))
        return mongoTemplate.find(query, Book::class.java)
    }
}

