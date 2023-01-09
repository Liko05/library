package com.library.service.impl

import com.library.client.AuthorsClient
import com.library.dto.author.AuthorBasic
import com.library.dto.author.AuthorDetailed
import com.library.mapper.AuthorMapper
import com.library.repository.BookRepository
import com.library.service.AuthorService
import org.slf4j.Logger
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
    private val logger: Logger = org.slf4j.LoggerFactory.getLogger(AuthorServiceImpl::class.java)
    override fun getAuthor(id: UUID): ResponseEntity<AuthorDetailed> {
        return try {
            val author = authorsClient.getAuthor(id)
            val books = repository.getBooksByAuthor(id)
            ResponseEntity.ok(mapper.mapMockAuthorDetailedToAuthorDetailed(author, books))
        } catch (e: Exception) {
            logger.error("Error while getting author with id $id", e)
            ResponseEntity.notFound().build()
        }
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
