package com.itau.password_validator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class PasswordValidatorApplication

fun main(args: Array<String>) {
	runApplication<PasswordValidatorApplication>(*args)
}
