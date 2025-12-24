package com.itau.password_validator.infrastructure

import com.itau.password_validator.infrastructure.v1.password.response.ValidatePasswordErrorResponse

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ValidatePasswordErrorResponse> {
        return ResponseEntity.badRequest().body(
            ValidatePasswordErrorResponse(
                code = 400,
                message = e.bindingResult.fieldError?.defaultMessage ?: e.message
            )
        )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ValidatePasswordErrorResponse> {
        return ResponseEntity.internalServerError().body(
            ValidatePasswordErrorResponse(
                code = 500,
                message = e.message?: "Unexpected error"
            )
        )
    }
}