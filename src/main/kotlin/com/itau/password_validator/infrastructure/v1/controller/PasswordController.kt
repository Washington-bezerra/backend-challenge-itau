package com.itau.password_validator.infrastructure.v1.controller

import com.itau.password_validator.infrastructure.v1.request.ValidatePasswordRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/password")
class PasswordController : IPasswordController {

    override fun validate(request: ValidatePasswordRequest): ResponseEntity<*> {
        return ResponseEntity.ok().build<Any>()
    }
}