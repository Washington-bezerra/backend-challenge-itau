package com.itau.password_validator.infrastructure

import com.itau.password_validator.infrastructure.config.LogUtils.logError
import com.itau.password_validator.infrastructure.v1.password.responses.ValidatePasswordErrorResponse

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ValidatePasswordErrorResponse> {
        logger.logError("Method argument validation failed", e)
        return ResponseEntity.badRequest().body(
            ValidatePasswordErrorResponse(
                code = 400,
                message = e.bindingResult.fieldError?.defaultMessage ?: e.message
            )
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ValidatePasswordErrorResponse> {
        logger.logError("Illegal argument exception", e)
        return ResponseEntity.badRequest().body(
            ValidatePasswordErrorResponse(
                code = 400,
                message = e.message ?: "Invalid argument"
            )
        )
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ValidatePasswordErrorResponse> {
        logger.logError("Illegal state exception", e)
        return ResponseEntity.internalServerError().body(
            ValidatePasswordErrorResponse(
                code = 500,
                message = e.message ?: "Invalid state"
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<ValidatePasswordErrorResponse> {
        logger.logError("Unexpected error occurred", e)
        return ResponseEntity.internalServerError().body(
            ValidatePasswordErrorResponse(
                code = 500,
                message = "Internal server error"
            )
        )
    }
}