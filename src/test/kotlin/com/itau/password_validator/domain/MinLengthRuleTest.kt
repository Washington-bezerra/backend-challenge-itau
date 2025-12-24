package com.itau.password_validator.domain

import com.itau.password_validator.domain.rules.MinLengthRule
import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MinLengthRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password is too short`(){
        every { messageProvider.getMessage("password.validation.min-length", 8) } returns "Password must have at least 8 characters"
        
        val rule = MinLengthRule(8, messageProvider)
        val result = rule.validate("pass")
        
        assertFalse(result.isValid)
        assertEquals("Password must have at least 8 characters", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password meets minimum length`(){
        val rule = MinLengthRule(8, messageProvider)
        val result = rule.validate("password")
        
        assertTrue(result.isValid)
    }
}