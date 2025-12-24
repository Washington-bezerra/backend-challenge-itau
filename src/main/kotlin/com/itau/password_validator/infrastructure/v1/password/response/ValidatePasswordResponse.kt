package com.itau.password_validator.infrastructure.v1.password.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidatePasswordResponse (
    @get:JsonProperty("isValid")
    val isValid: Boolean,
    val violations: List<String>

)