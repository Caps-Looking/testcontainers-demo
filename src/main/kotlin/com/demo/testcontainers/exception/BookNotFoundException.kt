package com.demo.testcontainers.exception

import java.util.UUID

class BookNotFoundException(id: UUID) : RuntimeException() {
    override val message: String = "Book with id: $id was Not Found"
}
