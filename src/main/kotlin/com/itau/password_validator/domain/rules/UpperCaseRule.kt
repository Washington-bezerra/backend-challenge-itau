package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class UpperCaseRule(val minUppercase: Int, val messageProvider: MessageProvider) : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        var contUpperCase = 0

        password.forEach {
            if (it.isUpperCase()) {
                contUpperCase += 1
                if (contUpperCase >= minUppercase){ return PasswordValidate(true) }
            }
        }

        return PasswordValidate(
            isValid = false,
            errorMessage = messageProvider.getMessage(
                "password.validation.uppercase",
                minUppercase
            )
        )
    }
}