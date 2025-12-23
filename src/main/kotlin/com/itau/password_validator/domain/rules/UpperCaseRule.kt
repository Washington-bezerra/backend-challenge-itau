package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class UpperCaseRule : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        password.forEach {
            if (it.isUpperCase()) {
                return PasswordValidate(true)
            }
        }

        return PasswordValidate(isValid = false, errorMessage = "A senha deve conter ao menos uma letra minúscula")
    }
}