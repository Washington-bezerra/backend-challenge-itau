package com.itau.password_validator.domain.entities

data class PasswordValidate(
    val isValid: Boolean,
    val errorMessage: String? = null
)
