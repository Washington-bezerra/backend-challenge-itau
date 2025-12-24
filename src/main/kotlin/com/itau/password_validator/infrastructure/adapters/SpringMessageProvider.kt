package com.itau.password_validator.infrastructure.adapters

import com.itau.password_validator.domain.interfaces.MessageProvider
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.*

@Component
class SpringMessageProvider(
    private val messageSource: MessageSource
) : MessageProvider {
    
    override fun getMessage(key: String, vararg args: Any): String {
        return messageSource.getMessage(key, args, Locale.ENGLISH)
    }
}