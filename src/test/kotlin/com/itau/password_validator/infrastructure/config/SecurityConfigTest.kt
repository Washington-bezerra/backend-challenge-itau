package com.itau.password_validator.infrastructure.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureWebMvc
class SecurityConfigTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return 401 when X-API-Key header is missing`() {
        mockMvc.perform(
            post("/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"password": "TestPass123!"}""")
        )
        .andExpect(status().isUnauthorized)
        .andExpect(jsonPath("$.error").value("Invalid or missing X-API-Key header"))
    }

    @Test
    fun `should return 401 when X-API-Key header is invalid`() {
        mockMvc.perform(
            post("/v1/password/validate")
                .header("X-API-Key", "invalid-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"password": "TestPass123!"}""")
        )
        .andExpect(status().isUnauthorized)
        .andExpect(jsonPath("$.error").value("Invalid or missing X-API-Key header"))
    }

    @Test
    fun `should allow access when X-API-Key header is valid`() {
        mockMvc.perform(
            post("/v1/password/validate")
                .header("X-API-Key", "itau-challenge-2024")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"password": "TestPass123!"}""")
        )
        .andExpect(status().isOk)
    }
}