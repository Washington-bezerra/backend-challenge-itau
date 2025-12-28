package com.itau.password_validator.infrastructure.v1.password.responses

data class ValidatePasswordErrorResponse (
    val code: Int,
    val message: String
)