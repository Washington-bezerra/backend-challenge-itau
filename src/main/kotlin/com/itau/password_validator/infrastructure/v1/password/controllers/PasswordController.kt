package com.itau.password_validator.infrastructure.v1.password.controllers

import com.itau.password_validator.application.usecases.ValidatePasswordUseCase
import com.itau.password_validator.infrastructure.config.LogUtils.logError
import com.itau.password_validator.infrastructure.config.LogUtils.logStructured
import com.itau.password_validator.infrastructure.v1.password.requests.ValidatePasswordRequest
import com.itau.password_validator.infrastructure.v1.password.responses.ValidatePasswordResponse
import org.slf4j.LoggerFactory
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

    private val logger = LoggerFactory.getLogger(PasswordController::class.java)

    @PostMapping("/validate")
    override fun validate(
        @RequestHeader("X-API-Key", required = true) apiKey: String,
        @Valid @RequestBody request: ValidatePasswordRequest
    ): ResponseEntity<ValidatePasswordResponse> {
        try {
            val result = validatePasswordUseCase(request.password!!)
            
            logger.logStructured("Password validation completed", "REQUEST_COMPLETED",
                "isValid" to result.isValid, "violationsCount" to result.violations.size)

            return ResponseEntity.ok().body(
                ValidatePasswordResponse(
                    isValid = result.isValid,
                    violations = result.violations
                )
            )
        } catch (e: Exception) {
            logger.logError("Error during password validation", e)
            throw e
        }
    }
}