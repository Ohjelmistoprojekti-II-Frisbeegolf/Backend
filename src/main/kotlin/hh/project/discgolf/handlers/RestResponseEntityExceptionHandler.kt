package hh.project.discgolf.handlers

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleElementNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleInvalidRequest(e: DataIntegrityViolationException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    // When updating stroke score, handles JSON parse error if given value is not type of Int
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJSONParseError(e: HttpMessageNotReadableException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
}