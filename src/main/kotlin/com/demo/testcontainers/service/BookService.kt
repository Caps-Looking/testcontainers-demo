package com.demo.testcontainers.service

import com.demo.testcontainers.exception.BookNotFoundException
import com.demo.testcontainers.model.Book
import com.demo.testcontainers.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BookService(private val bookRepository: BookRepository) {

    fun create(book: Book): Book = bookRepository.save(book)

    fun getAll(): List<Book> = bookRepository.findAll()

    fun findById(id: UUID): Book = findBook(id)

    fun update(id: UUID, book: Book): Book = bookRepository.save(with(book) {
        findBook(id).copy(title = title, author = author)
    })

    fun delete(id: UUID) = bookRepository.delete(findBook(id))

    private fun findBook(id: UUID): Book = bookRepository.findById(id).orElseThrow { BookNotFoundException(id) }
}