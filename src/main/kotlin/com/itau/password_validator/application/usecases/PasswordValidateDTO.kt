package com.itau.password_validator.application.usecases

data class PasswordValidateDTO(
    val isValid: Boolean,
    val violations: List<String>
)
