package com.itau.password_validator.domain.rules

import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MinUpperCaseRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password has insufficient uppercase letters`(){
        every { messageProvider.getMessage("password.validation.uppercase", 1) } returns "Password must contain at least 1 uppercase letter"
        
        val rule = MinUpperCaseRule(1, messageProvider)
        val result = rule.validate("password")
        
        assertFalse(result.isValid)
        assertEquals("Password must contain at least 1 uppercase letter", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has sufficient uppercase letters`(){
        val rule = MinUpperCaseRule(1, messageProvider)
        val result = rule.validate("Password")
        
        assertTrue(result.isValid)
    }
}