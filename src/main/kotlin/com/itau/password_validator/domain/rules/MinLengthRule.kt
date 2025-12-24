package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import com.itau.password_validator.domain.interfaces.MessageProvider
import java.util.*

class MinLengthRule(private val minLength: Int, private val messageProvider: MessageProvider) : PasswordRule {

    override fun validate(password: String): PasswordValidate {
        return if (password.length >= minLength) {
            PasswordValidate(isValid = true)
        } else {
            PasswordValidate(
                isValid = false, 
                errorMessage = messageProvider.getMessage(
                    "password.validation.min-length",
                    minLength
                )
            )
        }
    }
}