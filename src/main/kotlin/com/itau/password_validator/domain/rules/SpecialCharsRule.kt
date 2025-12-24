package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.Locale

class SpecialCharsRule(val specialChars: String, val minSpecialChars: Int, val messageSource: MessageSource) : PasswordRule {

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
            errorMessage = messageSource.getMessage(
                "password.validation.special-char",
                arrayOf(minSpecialChars.toString(), specialChars),
                Locale.getDefault()
            )
        )
    }
}