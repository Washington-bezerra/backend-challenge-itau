package com.itau.password_validator.infrastructure.v1.password.request

import jakarta.validation.constraints.NotBlank

data class ValidatePasswordRequest(

    @NotBlank(message = "Password must be not blank")
    val password: String?,
)
