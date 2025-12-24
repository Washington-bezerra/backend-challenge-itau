package com.itau.password_validator.domain.interfaces

interface MessageProvider {
    fun getMessage(key: String, vararg args: Any): String
}