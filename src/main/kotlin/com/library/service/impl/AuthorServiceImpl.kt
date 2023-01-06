package com.library.service.impl

import com.library.client.AuthorsClient
import com.library.dto.author.AuthorBasic
import com.library.dto.author.AuthorDetailed
import com.library.mapper.AuthorMapper
import com.library.repository.BookRepository
import com.library.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthorServiceImpl @Autowired constructor(
    private val authorsClient: AuthorsClient,
    private val repository: BookRepository,
    private val mapper: AuthorMapper
) : AuthorService {

    override fun getAuthor(id: UUID): ResponseEntity<AuthorDetailed> {
        val author = authorsClient.getAuthor(id)
        val books = repository.getBooksByAuthor(id)
        return ResponseEntity.ok(mapper.mapMockAuthorDetailedToAuthorDetailed(author, books))
    }

    override fun getAllAuthors(): ResponseEntity<List<AuthorBasic>> {
        val authors = authorsClient.getAuthors()
        val authorsBasic: MutableList<AuthorBasic> = mutableListOf()
        authors.forEach { author ->
            authorsBasic.add(mapper.mapMockAuthorDetailedToAuthorBasic(authorsClient.getAuthor(author.id)))
        }
        return ResponseEntity.ok(authorsBasic)
    }
}
