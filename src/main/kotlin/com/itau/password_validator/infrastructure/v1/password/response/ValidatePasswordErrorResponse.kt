package com.itau.password_validator.infrastructure.v1.password.response

data class ValidatePasswordErrorResponse (
    val code: Int,
    val message: String
)