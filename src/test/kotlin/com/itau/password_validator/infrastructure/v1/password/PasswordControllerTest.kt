package com.itau.password_validator.infrastructure.v1.password

import com.fasterxml.jackson.databind.ObjectMapper
import com.itau.password_validator.infrastructure.v1.password.request.ValidatePasswordRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.hamcrest.Matchers.hasSize
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class PasswordControllerTest(
    @Autowired
    val mockMvc: MockMvc,
    @Autowired
    val objectMapper: ObjectMapper
) {

    @Test
    fun `should return success when password is valid`() {
        val request = ValidatePasswordRequest("1Pasword!")

        mockMvc.perform(
            post("/api/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isValid").value(true))
            .andExpect(jsonPath("$.violations").isEmpty())
    }

    @Test
    fun `should return validation error when password is invalid`() {
        val request = ValidatePasswordRequest("1Pass word!")

        mockMvc.perform(
            post("/api/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isValid").value(false))
            .andExpect(jsonPath("$.violations", hasSize<String>(2)))
    }

    @Test
    fun `should return validation error when password is null`() {
        val request = ValidatePasswordRequest(null)

        mockMvc.perform(
            post("/api/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("Password must be not blank"))
    }


}