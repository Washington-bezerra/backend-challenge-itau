package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.*

class LowerCaseRule(val minLowercase: Int, val messageSource: MessageSource) : PasswordRule {
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
            errorMessage = messageSource.getMessage(
                "password.validation.lowercase",
                arrayOf(minLowercase),
                Locale.getDefault()
            )
        )
    }
}