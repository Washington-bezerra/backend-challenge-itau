package com.itau.password_validator.infrastructure.v1.password.controller

import com.itau.password_validator.application.usecases.password.validate.ValidatePasswordUseCase
import com.itau.password_validator.infrastructure.v1.password.request.ValidatePasswordRequest
import com.itau.password_validator.infrastructure.v1.password.response.ValidatePasswordResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("api/v1/password")
class PasswordController (
    val validatePasswordUseCase: ValidatePasswordUseCase
): IPasswordController {

    override fun validate(@Valid @RequestBody request: ValidatePasswordRequest): ResponseEntity<*> {

        val result = validatePasswordUseCase(request.password!!)

        return ResponseEntity.ok().body(
            ValidatePasswordResponse(
                isValid = result.isValid,
                violations = result.violations
            )
        )
    }
}