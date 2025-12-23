package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class DigitRule : PasswordRule {
    override fun validate(password: String): PasswordValidate {
        password.forEach {
            if (it.isDigit()) return PasswordValidate(isValid = true)
        }

        return PasswordValidate(isValid = false, errorMessage = "A senha deve conter ao menos um dígito")
    }
}