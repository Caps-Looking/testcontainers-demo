package com.demo.testcontainers.integration

import com.demo.testcontainers.integration.base.BaseIntegrationTest
import com.demo.testcontainers.integration.seeds.BookSeeds
import com.demo.testcontainers.repository.BookRepository
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.UUID

class BookIntegrationTest : BaseIntegrationTest() {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var bookRepository: BookRepository

    private val bookId = UUID.fromString("1e162d4e-66a4-47ba-85bd-9019dedc30a2")

    @BeforeEach
    fun setup() {
        cleanDB()
        BookSeeds(bookRepository).insertMultipleBooks()
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    fun `should return all books`() {
        val request = MockMvcRequestBuilders.get("/books")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
        val jsonArray = JSONArray(response.andReturn().response.contentAsString)

        assertEquals(jsonArray.length(), 3)
    }

    @Test
    fun `should return book by id`() {
        val request = MockMvcRequestBuilders.get("/books/$bookId")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        Assert.assertEquals(jsonObject.get("id"), bookId.toString())
        Assert.assertEquals(jsonObject.get("title"), "Title 1")
        Assert.assertEquals(jsonObject.get("author"), "Author 1")
    }

    @Test
    fun `should create book`() {
        val body = """
            {
                "title": "A title",
                "author": "An author"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated)
        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        Assert.assertNotNull(jsonObject.get("id"))
        Assert.assertEquals(jsonObject.get("title"), "A title")
        Assert.assertEquals(jsonObject.get("author"), "An author")
    }

    @Test
    fun `should update book`() {
        val body = """
            {
                "title": "Updated title",
                "author": "Updated author"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .put("/books/$bookId")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        Assert.assertEquals(jsonObject.get("id"), bookId.toString())
        Assert.assertEquals(jsonObject.get("title"), "Updated title")
        Assert.assertEquals(jsonObject.get("author"), "Updated author")
    }

    @Test
    fun `should delete book by id`() {
        val request = MockMvcRequestBuilders.delete("/books/$bookId")
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent)
    }
}
