package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MinDigitRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password has insufficient digits`(){
        every {
            messageProvider.getMessage("password.validation.digit", 1)
        } returns "Password must contain at least 1 digit"
        
        val rule = MinDigitRule(1, messageProvider)
        val result = rule.validate("password")
        
        assertFalse(result.isValid)
        assertEquals("Password must contain at least 1 digit", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has sufficient digits`(){
        val rule = MinDigitRule(1, messageProvider)
        val result = rule.validate("password1")
        
        assertTrue(result.isValid)
    }
}