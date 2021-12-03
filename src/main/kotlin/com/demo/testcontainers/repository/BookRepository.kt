package com.demo.testcontainers.repository

import com.demo.testcontainers.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BookRepository : JpaRepository<Book, UUID>
