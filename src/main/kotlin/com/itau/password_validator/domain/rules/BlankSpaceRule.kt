package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class BlankSpaceRule : PasswordRule {
    override fun validate(password: String): PasswordValidate{
        password.forEach {
            if (it == ' ') {
                return PasswordValidate(isValid = false, errorMessage = "A senha não deve conter espaço em branco")
            }
        }

        return PasswordValidate(isValid = true)
    }
}