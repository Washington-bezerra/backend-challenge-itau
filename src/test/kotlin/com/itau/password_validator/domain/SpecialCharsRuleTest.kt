package com.itau.password_validator.domain

import com.itau.password_validator.domain.rules.SpecialCharsRule
import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SpecialCharsRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password has insufficient special characters`(){
        every { messageProvider.getMessage("password.validation.special-char", 1, "!@#$%^&*()-+") } returns "Password must contain at least 1 special character: !@#$%^&*()-+"
        
        val rule = SpecialCharsRule("!@#$%^&*()-+", 1, messageProvider)
        val result = rule.validate("password")
        
        assertFalse(result.isValid)
        assertEquals("Password must contain at least 1 special character: !@#$%^&*()-+", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has sufficient special characters`(){
        val rule = SpecialCharsRule("!@#$%^&*()-+", 1, messageProvider)
        val result = rule.validate("password!")
        
        assertTrue(result.isValid)
    }
}