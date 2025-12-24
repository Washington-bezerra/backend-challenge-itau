package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.*

class UpperCaseRule(val minUppercase: Int, val messageSource: MessageSource) : PasswordRule {
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
            errorMessage = messageSource.getMessage(
                "password.validation.uppercase",
                arrayOf(minUppercase),
                Locale.getDefault()
            )
        )
    }
}