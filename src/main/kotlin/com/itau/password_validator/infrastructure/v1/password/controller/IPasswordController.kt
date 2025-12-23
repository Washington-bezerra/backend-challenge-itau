package com.itau.password_validator.infrastructure.v1.password.controller

import com.itau.password_validator.infrastructure.v1.password.request.ValidatePasswordRequest
import com.itau.password_validator.infrastructure.v1.password.response.ValidatePasswordResponse
import com.itau.password_validator.infrastructure.v1.password.response.ValidatePasswordErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import jakarta.validation.Valid

@Tag(name = "Password", description = "API para validação de senha")
interface IPasswordController {

    @Operation(summary = "Valida uma senha")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Validation completed successfully",
                content = [Content(schema = Schema(implementation = ValidatePasswordResponse::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request data",
                content = [Content(schema = Schema(implementation = ValidatePasswordErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = [Content(schema = Schema(implementation = ValidatePasswordErrorResponse::class))]
            )
        ]
    )
    @PostMapping("/validate")
    fun validate(
        @Valid @RequestBody(required = true) request: ValidatePasswordRequest
    ) : ResponseEntity<*>
}