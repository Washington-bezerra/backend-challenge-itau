package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate
import org.springframework.context.MessageSource
import java.util.Locale

class BlankSpaceRule(val messageSource: MessageSource) : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        password.forEach {
            if (it.isWhitespace()) {
                return PasswordValidate(
                    isValid = false,
                    errorMessage = messageSource.getMessage(
                        "password.validation.no-spaces",
                        arrayOf(),
                        Locale.getDefault()
                    )
                )
            }
        }

        return PasswordValidate(isValid = true)
    }
}