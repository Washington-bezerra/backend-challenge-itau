package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class NoRepeatedCharsRule : PasswordRule {
    override fun validate(password: String): PasswordValidate {
        val hasRepeatedChars = password.toSet().size != password.length

        return if (hasRepeatedChars) {
            PasswordValidate(isValid = false, errorMessage = "A senha não deve conter caracteres repetidos.")
        }else{
            PasswordValidate(isValid = true)
        }
    }
}