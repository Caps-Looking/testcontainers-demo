package com.demo.testcontainers.exception.handler

import com.demo.testcontainers.exception.BookNotFoundException
import com.demo.testcontainers.model.Book
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BookNotFoundException::class)
    fun bookNotFoundException(e: BookNotFoundException) = ResponseEntity(ErrorMessage(e.message), HttpStatus.NOT_FOUND)
}

data class ErrorMessage(
    val message: String? = null
)
