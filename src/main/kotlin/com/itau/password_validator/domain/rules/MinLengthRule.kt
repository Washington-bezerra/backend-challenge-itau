package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.entities.PasswordValidate

class MinLengthRule : PasswordRule {

    companion object {
        const val MIN_LENGTH = 9
    }

    override fun validate(password: String): PasswordValidate{
        return if (password.length >= MIN_LENGTH){
            PasswordValidate(isValid = true)
        }else{
            PasswordValidate(isValid = false, errorMessage = "A senha deve conter no mínimo 9 caracteres.")
        }
    }
}