package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class SpecialCharsRule : PasswordRule {

    companion object{
        const val SPECIAL_CHARS = "!@#$%^&*()-+"
    }
    override fun validate(password: String): PasswordValidate {
        password.forEach {
            if (SPECIAL_CHARS.contains(it)) return PasswordValidate(isValid = true)
        }

        return PasswordValidate(isValid = false, errorMessage = "A senha deve conter ao menos um dos seguintes caracteres especiais: !@#$%^&*()-+")
    }
}