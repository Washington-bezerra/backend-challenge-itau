package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoRepeatedCharsRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password has repeated characters`(){
        every { messageProvider.getMessage("password.validation.no-repeated") } returns "Password must not contain repeated characters"
        
        val rule = NoRepeatedCharsRule(messageProvider)
        val result = rule.validate("password")
        
        assertFalse(result.isValid)
        assertEquals("Password must not contain repeated characters", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has no repeated characters`(){
        val rule = NoRepeatedCharsRule(messageProvider)
        val result = rule.validate("pasword")
        
        assertTrue(result.isValid)
    }
}