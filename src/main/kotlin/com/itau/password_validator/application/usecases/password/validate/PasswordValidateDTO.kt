package com.itau.password_validator.application.usecases.password.validate

data class PasswordValidateDTO(
    val password: String,
    val isValid: Boolean,
    val violations: List<String>
)
