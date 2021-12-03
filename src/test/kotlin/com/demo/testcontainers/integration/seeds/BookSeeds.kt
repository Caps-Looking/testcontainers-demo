package com.demo.testcontainers.integration.seeds

import com.demo.testcontainers.model.Book
import com.demo.testcontainers.repository.BookRepository
import java.util.UUID

class BookSeeds(private val bookRepository: BookRepository) {

    fun insertMultipleBooks() {
        bookRepository.saveAll(
            listOf(
                Book(
                    id = UUID.fromString("1e162d4e-66a4-47ba-85bd-9019dedc30a2"),
                    title = "Title 1",
                    author = "Author 1"
                ),
                Book(
                    id = UUID.fromString("1b5ba5f8-b99f-4421-8f0c-7048263a10bb"),
                    title = "Title 2",
                    author = "Author 2"
                ),
                Book(
                    id = UUID.fromString("dd11fc32-bcaa-414d-9c8c-89a50bd05acc"),
                    title = "Title 3",
                    author = "Author 3"
                )
            )
        )
    }
}