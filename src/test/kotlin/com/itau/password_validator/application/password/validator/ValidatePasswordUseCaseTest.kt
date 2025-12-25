package com.itau.password_validator.application.password.validator

import com.itau.password_validator.application.usecases.password.validate.ValidatePasswordUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest
class ValidatePasswordUseCaseTest {

    @Autowired
    lateinit var useCase: ValidatePasswordUseCase

    @Test
    fun `should return success when all prerequisites met`(){
        val result = useCase("1Pasword!")

        assertTrue(result.isValid)
        assertTrue(result.violations.isEmpty())
    }

    @Test
    fun `should return digit violation when password not contain min digits`(){
        val result = useCase("Paswordi!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must contain at least 1 digit", result.violations[0])
    }

    @Test
    fun `should return lower case violation when password not contain min lower case`(){
        val result = useCase("1PASWORD!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must contain at least 1 lowercase letter", result.violations[0])
    }

    @Test
    fun `should return length violation when password not contain min length`(){
        val result = useCase("1Paword!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must have at least 9 characters", result.violations[0])
    }

    @Test
    fun `should return repeated violation when password contain repetition`(){
        val result = useCase("1Password!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must not contain repeated characters", result.violations[0])
    }

    @Test
    fun `should return special char violation when password not contain special char`(){
        val result = useCase("1Paswordi")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals(
            "Password must contain at least 1 special character: !@#$%^&*()-+",
            result.violations[0]
        )
    }

    @Test
    fun `should return upper case violation when password not contain min upper case`(){
        val result = useCase("1pasword!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals(
            "Password must contain at least 1 uppercase letter",
            result.violations[0]
        )
    }

    @Test
    fun `should return white space violation when password contain white space`(){
        val result = useCase("1 Pasword!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals(
            "Password must not contain blank spaces",
            result.violations[0]
        )
    }

    @Test
    fun `should return all violations when password violates all rules`(){
        val result = useCase(", ,")

        assertFalse(result.isValid)
        assertEquals(7, result.violations.size)
    }
}