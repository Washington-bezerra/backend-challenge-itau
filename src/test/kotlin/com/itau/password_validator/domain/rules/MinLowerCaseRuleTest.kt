package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MinLowerCaseRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password has insufficient lowercase letters`(){
        every {
            messageProvider.getMessage("password.validation.lowercase", 1)
        } returns "Password must contain at least 1 lowercase letter"
        
        val rule = MinLowerCaseRule(1, messageProvider)
        val result = rule.validate("PASSWORD")
        
        assertFalse(result.isValid)
        assertEquals("Password must contain at least 1 lowercase letter", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has sufficient lowercase letters`(){
        val rule = MinLowerCaseRule(1, messageProvider)
        val result = rule.validate("Password")
        
        assertTrue(result.isValid)
    }
}