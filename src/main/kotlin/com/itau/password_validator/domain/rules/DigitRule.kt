package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.Locale

class DigitRule(val minDigits: Int, val messageSource: MessageSource) : PasswordRule {
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
            errorMessage = messageSource.getMessage(
                "password.validation.digit",
                arrayOf(minDigits),
                Locale.getDefault()
            )
        )
    }
}