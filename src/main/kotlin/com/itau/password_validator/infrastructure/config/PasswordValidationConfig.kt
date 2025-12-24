package com.itau.password_validator.infrastructure.config

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
    fun minLengthRule(properties: PasswordValidationProperties, messageSource: MessageSource) = 
        MinLengthRule(properties.minLength, messageSource)

    @Bean
    fun upperCaseRule(properties: PasswordValidationProperties, messageSource: MessageSource) = UpperCaseRule(properties.minUppercase, messageSource)

    @Bean
    fun lowerCaseRule(properties: PasswordValidationProperties, messageSource: MessageSource) = LowerCaseRule(properties.minLowercase, messageSource)

    @Bean
    fun digitRule(properties: PasswordValidationProperties, messageSource: MessageSource) = DigitRule(properties.minDigits, messageSource)

    @Bean
    fun specialCharsRule(properties: PasswordValidationProperties, messageSource: MessageSource) = 
        SpecialCharsRule(properties.specialChars, properties.minSpecialChars, messageSource)

    @Bean
    fun blankSpaceRule(messageSource: MessageSource) = BlankSpaceRule(messageSource)

    @Bean
    fun noRepeatedCharsRule(messageSource: MessageSource) = NoRepeatedCharsRule(messageSource)
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