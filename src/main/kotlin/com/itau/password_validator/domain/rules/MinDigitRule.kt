package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class MinDigitRule(val minDigits: Int, val messageProvider: MessageProvider) : PasswordRule {
    override fun validate(password: String): PasswordValidate {
        var countDigits = 0

        password.forEach {
            if (it.isDigit()) {
                countDigits += 1
                if (countDigits >= minDigits){ return PasswordValidate(isValid = true) }
            }
        }

        return PasswordValidate(
            isValid = false,
            errorMessage = messageProvider.getMessage(
                "password.validation.digit",
                minDigits
            )
        )
    }
}