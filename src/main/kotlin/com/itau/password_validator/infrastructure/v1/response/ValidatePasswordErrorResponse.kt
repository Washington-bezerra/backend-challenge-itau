package com.itau.password_validator.infrastructure.v1.response

data class ValidatePasswordErrorResponse (
    val isValid: Boolean,
    val violations: List<String>
)