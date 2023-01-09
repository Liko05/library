package com.library.controller

import com.library.dto.author.AuthorBasic
import com.library.dto.author.AuthorDetailed
import com.library.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/authors")
class AuthorController @Autowired constructor(
    private val authorService: AuthorService
){
    @GetMapping("")
    fun getAllAuthors(): ResponseEntity<List<AuthorBasic>> {
        return authorService.getAllAuthors()
    }
    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: UUID): ResponseEntity<AuthorDetailed> {
        return authorService.getAuthor(id)
    }
}