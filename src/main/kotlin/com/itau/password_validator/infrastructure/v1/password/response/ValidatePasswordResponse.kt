package com.itau.password_validator.infrastructure.v1.password.response

data class ValidatePasswordResponse (
    val isValid: Boolean,
    val violations: List<String>
)