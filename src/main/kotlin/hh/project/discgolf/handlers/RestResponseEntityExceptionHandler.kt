package hh.project.discgolf.handlers

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import org.hibernate.exception.DataException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.security.auth.login.CredentialException

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

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleBadMethodArgument(e: MethodArgumentTypeMismatchException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(JsonParseException::class)
    fun handleGsonParseError( e : JsonSyntaxException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingParameter( e : MissingServletRequestParameterException) : ResponseEntity<String> =
        ResponseEntity(e.message , HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NumberFormatException::class)
    fun handleNumberFormatException( e: NumberFormatException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrorInNewUserValidation( e: MethodArgumentNotValidException) : ResponseEntity<List<String?>> =
        ResponseEntity(e.bindingResult.allErrors.map { it.defaultMessage }, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(CredentialException::class)
    fun handleWrongCredentials( e : CredentialException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)

    @ExceptionHandler(DataException::class)
    fun handlePasswordsDontMatch(e: DataException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)
}