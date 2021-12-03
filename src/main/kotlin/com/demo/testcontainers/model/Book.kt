package com.demo.testcontainers.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Book(
    @Id
    val id: UUID = UUID.randomUUID(),
    val title: String? = null,
    val author: String? = null
)
