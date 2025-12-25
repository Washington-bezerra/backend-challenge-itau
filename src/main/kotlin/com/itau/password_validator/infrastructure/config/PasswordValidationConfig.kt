package com.itau.password_validator.infrastructure.config

import com.itau.password_validator.domain.interfaces.MessageProvider
import com.itau.password_validator.domain.rules.*
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.MessageSource
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
@EnableConfigurationProperties(PasswordValidationProperties::class)
class PasswordValidationConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        return messageSource
    }

    @Bean
    fun minLengthRule(properties: PasswordValidationProperties, messageProvider: MessageProvider) = 
        MinLengthRule(properties.minLength, messageProvider)

    @Bean
    fun upperCaseRule(properties: PasswordValidationProperties, messageProvider: MessageProvider) =
        MinUpperCaseRule(properties.minUppercase, messageProvider)

    @Bean
    fun lowerCaseRule(properties: PasswordValidationProperties, messageProvider: MessageProvider) =
        MinLowerCaseRule(properties.minLowercase, messageProvider)

    @Bean
    fun digitRule(properties: PasswordValidationProperties, messageProvider: MessageProvider) =
        MinDigitRule(properties.minDigits, messageProvider)

    @Bean
    fun specialCharsRule(properties: PasswordValidationProperties, messageProvider: MessageProvider) = 
        SpecialCharsRule(properties.specialChars, properties.minSpecialChars, messageProvider)

    @Bean
    fun blankSpaceRule(messageProvider: MessageProvider) = WhiteSpaceRule(messageProvider)

    @Bean
    fun noRepeatedCharsRule(messageProvider: MessageProvider) = NoRepeatedCharsRule(messageProvider)
}

@ConfigurationProperties(prefix = "password.validation")
data class PasswordValidationProperties(
    val minLength: Int = 8,
    val minUppercase: Int = 1,
    val minLowercase: Int = 1,
    val minDigits: Int = 1,
    val minSpecialChars: Int = 1,
    val specialChars: String = "!@#$%^&*()-+"
)