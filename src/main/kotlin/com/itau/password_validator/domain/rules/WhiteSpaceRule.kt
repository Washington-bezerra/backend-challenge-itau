package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class WhiteSpaceRule(val messageProvider: MessageProvider) : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        password.forEach {
            if (it.isWhitespace()) {
                return PasswordValidate(
                    isValid = false,
                    errorMessage = messageProvider.getMessage(
                        "password.validation.no-spaces"
                    )
                )
            }
        }

        return PasswordValidate(isValid = true)
    }
}