package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.*

class MinLengthRule(private val minLength: Int, private val messageSource: MessageSource) : PasswordRule {

    override fun validate(password: String): PasswordValidate {
        return if (password.length >= minLength) {
            PasswordValidate(isValid = true)
        } else {
            PasswordValidate(
                isValid = false, 
                errorMessage = messageSource.getMessage(
                    "password.validation.min-length",
                    arrayOf(minLength),
                    Locale.getDefault()
                )
            )
        }
    }
}