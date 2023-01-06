package com.library.client

import com.library.dto.author.MockAuthorBasic
import com.library.dto.author.MockAuthorDetailed
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.UUID

@FeignClient(name = "authors", url = "https://d35k5.mocklab.io/")
@Cacheable("authors")
interface AuthorsClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["/authors"])
    fun getAuthors(): List<MockAuthorBasic>

    @RequestMapping(method = [RequestMethod.GET], value = ["/authors/{id}"])
    fun getAuthor(@PathVariable id: UUID): MockAuthorDetailed
}