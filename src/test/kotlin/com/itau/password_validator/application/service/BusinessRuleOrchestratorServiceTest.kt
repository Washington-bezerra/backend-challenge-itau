package com.itau.password_validator.application.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
class BusinessRuleOrchestratorServiceTest {

    @Autowired
    lateinit var businessRuleOrchestratorService: BusinessRuleOrchestratorService

    @Test
    fun `should return success when all prerequisites met`() {
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1!Pasword")

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should return digit violation when password not contain min digits`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("Paswordi!")

        assertEquals(1, result.size)
        assertEquals("Password must contain at least 1 digit", result[0])
    }

    @Test
    fun `should return lower case violation when password not contain min lower case`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1PASWORD!")

        assertEquals(1, result.size)
        assertEquals("Password must contain at least 1 lowercase letter", result[0])
    }

    @Test
    fun `should return length violation when password not contain min length`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1Paword!")

        assertEquals(1, result.size)
        assertEquals("Password must have at least 9 characters", result[0])
    }

    @Test
    fun `should return repeated violation when password contain repetition`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1Password!")

        assertEquals(1, result.size)
        assertEquals("Password must not contain repeated characters", result[0])
    }

    @Test
    fun `should return special char violation when password not contain special char`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1Paswordi")

        assertEquals(1, result.size)
        assertEquals("Password must contain at least 1 special character: !@#\$%^&*()-+", result[0])
    }

    @Test
    fun `should return upper case violation when password not contain min upper case`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1pasword!")

        assertEquals(1, result.size)
        assertEquals("Password must contain at least 1 uppercase letter", result[0])
    }

    @Test
    fun `should return white space violation when password contain white space`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule("1 Pasword!")

        assertEquals(1, result.size)
        assertEquals("Password must not contain blank spaces", result[0])
    }

    @Test
    fun `should return all violations when password violates all rules`(){
        val result = businessRuleOrchestratorService.applyAllBusinessRule(",, ")

        assertEquals(7, result.size)
    }
}