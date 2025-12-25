package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class MinLowerCaseRule(val minLowercase: Int, val messageProvider: MessageProvider) : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        var countLowerCase = 0

        password.forEach {
            if (it.isLowerCase()) {
                countLowerCase += 1
                if (countLowerCase >= minLowercase){ return PasswordValidate(true) }
            }
        }

        return PasswordValidate(
            isValid = false,
            errorMessage = messageProvider.getMessage(
                "password.validation.lowercase",
                minLowercase
            )
        )
    }
}