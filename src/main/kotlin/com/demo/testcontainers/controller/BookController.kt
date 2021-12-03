package com.demo.testcontainers.controller

import com.demo.testcontainers.model.Book
import com.demo.testcontainers.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @PostMapping
    fun create(@RequestBody book: Book): ResponseEntity<Book> {
        val newBook = bookService.create(book)
        return ResponseEntity.created(URI.create("/books/${newBook.id}")).body(newBook)
    }

    @GetMapping
    fun getBooks() = ResponseEntity.ok(bookService.getAll())

    @GetMapping("/{id}")
    fun findBookById(@PathVariable id: UUID) = ResponseEntity.ok(bookService.findById(id))

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody book: Book) = ResponseEntity.ok(bookService.update(id, book))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<HttpStatus> {
        bookService.delete(id)
        return ResponseEntity.noContent().build()
    }
}