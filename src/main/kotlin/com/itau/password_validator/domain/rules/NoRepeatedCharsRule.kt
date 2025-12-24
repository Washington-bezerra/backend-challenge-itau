package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.Locale

class NoRepeatedCharsRule(val messageSource: MessageSource) : PasswordRule {
    override fun validate(password: String): PasswordValidate {
        val hasRepeatedChars = password.toSet().size != password.length

        return if (hasRepeatedChars) {
            PasswordValidate(
                isValid = false,
                errorMessage = messageSource.getMessage(
                    "password.validation.no-repeated",
                    arrayOf(),
                    Locale.getDefault()
                )
            )
        }else{
            PasswordValidate(isValid = true)
        }
    }
}