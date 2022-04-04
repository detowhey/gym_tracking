package com.almeida.henrique.gym_tracking.resource.excepetions

import com.almeida.henrique.gym_tracking.services.exception.DataBaseException
import com.almeida.henrique.gym_tracking.services.exception.ResourceNotFoundException
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
        return responseEntity(HttpStatus.NOT_FOUND, exception, request, "Resource not found")
    }

    @ExceptionHandler(DataBaseException::class)
    fun dataBase(exception: DataBaseException, request: HttpServletRequest): ResponseEntity<StandardError> {
        return responseEntity(HttpStatus.BAD_REQUEST, exception, request, "Database error")
    }

    private fun responseEntity(
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
