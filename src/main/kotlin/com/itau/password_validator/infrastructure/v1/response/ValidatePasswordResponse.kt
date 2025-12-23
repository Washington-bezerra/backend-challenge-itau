package com.itau.password_validator.infrastructure.v1.response

data class ValidatePasswordResponse (
    val code: Int,
    val message: String
)