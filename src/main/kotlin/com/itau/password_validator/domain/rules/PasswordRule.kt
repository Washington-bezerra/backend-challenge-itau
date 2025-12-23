package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

interface PasswordRule {
    fun validate(password: String): PasswordValidate
}