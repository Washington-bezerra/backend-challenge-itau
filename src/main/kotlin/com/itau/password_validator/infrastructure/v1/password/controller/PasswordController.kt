package com.itau.password_validator.infrastructure.v1.password.controller

import com.itau.password_validator.application.usecases.password.validate.ValidatePasswordUseCase
import com.itau.password_validator.infrastructure.v1.password.request.ValidatePasswordRequest
import com.itau.password_validator.infrastructure.v1.password.response.ValidatePasswordResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@RestController
@RequestMapping("api/v1/password")
class PasswordController (
    val validatePasswordUseCase: ValidatePasswordUseCase
): IPasswordController {

    @PostMapping("/validate")
    override fun validate(
        @RequestHeader("X-API-Key", required = true) apiKey: String,
        @Valid @RequestBody request: ValidatePasswordRequest
    ): ResponseEntity<ValidatePasswordResponse> {

        val result = validatePasswordUseCase(request.password!!)

        return ResponseEntity.ok().body(
            ValidatePasswordResponse(
                isValid = result.isValid,
                violations = result.violations
            )
        )
    }
}