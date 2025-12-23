package com.itau.password_validator.infrastructure.v1.password.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ValidatePasswordRequest(

    @NotNull(message = "Password must be not null")
    @NotBlank(message = "Password must be not blank")
    val password: String?,
)
