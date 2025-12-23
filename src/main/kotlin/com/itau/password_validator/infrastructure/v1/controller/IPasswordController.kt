package com.itau.password_validator.infrastructure.v1.controller

import com.itau.password_validator.infrastructure.v1.request.ValidatePasswordRequest
import com.itau.password_validator.infrastructure.v1.response.ValidatePasswordErrorResponse
import com.itau.password_validator.infrastructure.v1.response.ValidatePasswordResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Password", description = "API para validação de senha")
interface IPasswordController {

    @Operation(summary = "Valida uma senha")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Password is valid",
                content = [Content(schema = Schema(implementation = ValidatePasswordResponse::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Request error",
                content = [Content(schema = Schema(implementation = ValidatePasswordErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "422",
                description = "Password is invalid",
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
        @RequestBody(required = true) request: ValidatePasswordRequest
    ) : ResponseEntity<*>
}