package com.itau.password_validator.application.password.validator

import com.itau.password_validator.application.service.BusinessRuleOrchestratorService
import com.itau.password_validator.application.usecases.password.validate.ValidatePasswordUseCase
import com.itau.password_validator.domain.interfaces.MessageProvider
import com.itau.password_validator.domain.rules.MinDigitRule
import com.itau.password_validator.domain.rules.MinLowerCaseRule
import com.itau.password_validator.domain.rules.MinLengthRule
import com.itau.password_validator.domain.rules.NoRepeatedCharsRule
import com.itau.password_validator.domain.rules.SpecialCharsRule
import com.itau.password_validator.domain.rules.MinUpperCaseRule
import com.itau.password_validator.domain.rules.WhiteSpaceRule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidatePasswordUseCaseTest {

    private val messageProvider = mockk<MessageProvider>()

    private val minLengthRule = MinLengthRule(9, messageProvider)
    private val minUpperCaseRule = MinUpperCaseRule(1, messageProvider)
    private val minLowerCaseRule = MinLowerCaseRule(1, messageProvider)
    private val minDigitRule = MinDigitRule(1, messageProvider)
    private val specialCharsRule = SpecialCharsRule("!@#$%^&*()-+", 1, messageProvider)
    private val whiteSpaceRule = WhiteSpaceRule(messageProvider)
    private val noRepeatedCharsRule = NoRepeatedCharsRule(messageProvider)

    private val businessRuleOrchestratorService = BusinessRuleOrchestratorService(
        minLengthRule,
        minUpperCaseRule,
        minLowerCaseRule,
        minDigitRule,
        specialCharsRule,
        whiteSpaceRule,
        noRepeatedCharsRule,
    )
    private val useCase = ValidatePasswordUseCase(businessRuleOrchestratorService)

    @Test
    fun `should return success when all prerequisites met`(){
        val result = useCase("1Pasword!")

        assertTrue(result.isValid)
        assertTrue(result.violations.isEmpty())
    }

    @Test
    fun `should return digit violation when password not contain min digits`(){
        every {
            messageProvider.getMessage("password.validation.digit", 1)
        } returns "Password must contain at least 1 digit"

        val result = useCase("Paswordi!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must contain at least 1 digit", result.violations[0])
    }

    @Test
    fun `should return lower case violation when password not contain min lower case`(){
        every {
            messageProvider.getMessage("password.validation.lowercase", 1)
        } returns "Password must contain at least 1 lowercase letter"

        val result = useCase("1PASWORD!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must contain at least 1 lowercase letter", result.violations[0])
    }

    @Test
    fun `should return length violation when password not contain min length`(){
        every {
            messageProvider.getMessage("password.validation.min-length", 9)
        } returns "Password must have at least 9 characters"

        val result = useCase("1Paword!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must have at least 9 characters", result.violations[0])
    }

    @Test
    fun `should return repeated violation when password contain repetition`(){
        every {
            messageProvider.getMessage("password.validation.no-repeated")
        } returns "Password must not contain repeated characters"

        val result = useCase("1Password!")

        assertFalse(result.isValid)
        assertEquals(1, result.violations.size)
        assertEquals("Password must not contain repeated characters", result.violations[0])
    }

    @Test
    fun `should return special char violation when password not contain special char`(){
        every {
            messageProvider.getMessage("password.validation.special-char", 1, "!@#$%^&*()-+")
        } returns "Password must contain at least 1 special character: !@#$%^&*()-+"

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
        every {
            messageProvider.getMessage("password.validation.uppercase", 1)
        } returns "Password must contain at least 1 uppercase letter"

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
        every {
            messageProvider.getMessage("password.validation.no-spaces")
        } returns "Password must not contain blank spaces"

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
        every { messageProvider.getMessage("password.validation.min-length", 9) } returns "Password must have at least 9 characters"
        every { messageProvider.getMessage("password.validation.uppercase", 1) } returns "Password must contain at least 1 uppercase letter"
        every { messageProvider.getMessage("password.validation.lowercase", 1) } returns "Password must contain at least 1 lowercase letter"
        every { messageProvider.getMessage("password.validation.digit", 1) } returns "Password must contain at least 1 digit"
        every { messageProvider.getMessage("password.validation.special-char", 1, "!@#$%^&*()-+") } returns "Password must contain at least 1 special character: !@#$%^&*()-+"
        every { messageProvider.getMessage("password.validation.no-spaces") } returns "Password must not contain blank spaces"
        every { messageProvider.getMessage("password.validation.no-repeated") } returns "Password must not contain repeated characters"

        val result = useCase(", ,")

        assertFalse(result.isValid)
        assertEquals(7, result.violations.size)
    }
}