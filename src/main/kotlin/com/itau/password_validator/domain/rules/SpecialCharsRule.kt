package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider

class SpecialCharsRule(val specialChars: String, val minSpecialChars: Int, val messageProvider: MessageProvider) : PasswordRule {

    override fun validate(password: String): PasswordValidate {
        var countSpecialChars = 0

        password.forEach {
            if (specialChars.contains(it)){
                countSpecialChars += 1
                if (countSpecialChars >= minSpecialChars) { return PasswordValidate(isValid = true) }
            }
        }

        return PasswordValidate(
            isValid = false,
            errorMessage = messageProvider.getMessage(
                "password.validation.special-char",
                minSpecialChars, specialChars
            )
        )
    }
}