package com.itau.password_validator.domain

import com.itau.password_validator.domain.rules.WhiteSpaceRule
import com.itau.password_validator.domain.interfaces.MessageProvider
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WhileSpaceRuleTest {

    private val messageProvider = mockk<MessageProvider>()

    @Test
    fun `should return violation when password contain while space`(){
        every { messageProvider.getMessage("password.validation.no-spaces") } returns "Password must not contain blank spaces"
        
        val rule = WhiteSpaceRule(messageProvider)
        val result = rule.validate("pass word")
        
        assertFalse(result.isValid)
        assertEquals("Password must not contain blank spaces", result.errorMessage)
    }
    
    @Test
    fun `should return no violation when password has no whitespace`(){
        val rule = WhiteSpaceRule(messageProvider)
        val result = rule.validate("password")
        
        assertTrue(result.isValid)
    }
}