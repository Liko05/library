package com.library

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class IntegrationTests @Autowired constructor(
	private val mockMvc: MockMvc
) {
	@Test
	fun testAuthors() {
		mockMvc.perform(get("/authors"))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	}
	@Test
	fun testAuthor(){
		mockMvc.perform(get("/authors/fef66227-8779-43f5-900a-9792bc2d3881"))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json("{\n" +
					"\t\"id\": \"fef66227-8779-43f5-900a-9792bc2d3881\",\n" +
					"\t\"fullName\": \"George Orwell\",\n" +
					"\t\"dateOfBirth\": \"1903-07-25\",\n" +
					"\t\"country\": \"USA\",\n" +
					"\t\"description\": \"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Pellentesque ipsum. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus rhoncus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam ante. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Nullam feugiat, turpis at pulvinar vulputate, erat libero tristique tellus, nec bibendum odio risus sit amet ante. Morbi scelerisque luctus velit. Sed ac dolor sit amet purus malesuada congue. Praesent dapibus. Aenean vel massa quis mauris vehicula lacinia. Aliquam ante. Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? Proin pede metus, vulputate nec, fermentum fringilla, vehicula vitae, justo. Nullam dapibus fermentum ipsum. Aliquam in lorem sit amet leo accumsan lacinia.\",\n" +
					"\t\"books\": []\n" +
					"}"))
	}
	@Test
	fun testBooks() {
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	@Test
	fun testBook(){
		mockMvc.perform(get("/books/11111111-1111-1111-1111-111111111111"))
			.andExpect(status().isNotFound)
	}
	@Test
	fun testAddingBook(){
		val result = mockMvc.perform(post("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\n" +
					"\t\"title\":\"Kniha6\",\n" +
					"\t\"authorId\":\"be87f642-fa3f-4c26-af43-7dd2bd99444e\",\n" +
					"\t\"description\":null,\n" +
					"\t\"releasedAt\":\"1900-01-01T12:00:00Z\"\n" +
					"}"))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andReturn()
		assert(result.response.contentAsString.contains("be87f642-fa3f-4c26-af43-7dd2bd99444e"))
	}
}
