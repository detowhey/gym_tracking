package com.almeida.henrique.gym_tracking.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExpectionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFound(
        exception: ResourceNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        return responseErrorEntity(HttpStatus.NOT_FOUND, exception, request, "Resource not found")
    }

    @ExceptionHandler(DataBaseException::class)
    fun dataBase(exception: DataBaseException, request: HttpServletRequest): ResponseEntity<StandardError> {
        return responseErrorEntity(HttpStatus.BAD_REQUEST, exception, request, "Database error")
    }

    @ExceptionHandler(ObjectRegisteredException::class)
    fun registredObject(
        exception: ObjectRegisteredException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        return responseErrorEntity(
            HttpStatus.BAD_REQUEST,
            exception,
            request,
            "Object already registered with this email"
        )
    }

    private fun responseErrorEntity(
        httpStatus: HttpStatus,
        exception: RuntimeException,
        request: HttpServletRequest,
        messageError: String
    ): ResponseEntity<StandardError> {
        val error =
            StandardError(
                Instant.now(),
                httpStatus.value(),
                messageError,
                exception.message,
                request.requestURI
            )
        return ResponseEntity.status(httpStatus).body(error)
    }
}
