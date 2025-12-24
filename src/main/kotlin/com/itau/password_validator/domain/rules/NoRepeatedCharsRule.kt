package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class NoRepeatedCharsRule(val messageProvider: MessageProvider) : PasswordRule {
    override fun validate(password: String): PasswordValidate {
        val hasRepeatedChars = password.toSet().size != password.length

        return if (hasRepeatedChars) {
            PasswordValidate(
                isValid = false,
                errorMessage = messageProvider.getMessage(
                    "password.validation.no-repeated"
                )
            )
        }else{
            PasswordValidate(isValid = true)
        }
    }
}