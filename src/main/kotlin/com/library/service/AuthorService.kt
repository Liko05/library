package com.library.service

import com.library.dto.author.AuthorBasic
import com.library.dto.author.AuthorDetailed
import org.springframework.http.ResponseEntity
import java.util.UUID

interface AuthorService {
    fun getAuthor(id: UUID): ResponseEntity<AuthorDetailed>
    fun getAllAuthors(): ResponseEntity<List<AuthorBasic>>
}