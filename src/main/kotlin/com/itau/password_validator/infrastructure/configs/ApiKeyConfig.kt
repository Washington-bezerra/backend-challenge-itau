package com.itau.password_validator.infrastructure.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.itau.password_validator.infrastructure.v1.password.responses.ValidatePasswordErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class ApiKeyConfig(
    @Value("\${api.key:itau-challenge}") private val apiKey: String
) {

    @Bean
    fun apiKeyFilter(): FilterRegistrationBean<ApiKeyFilter> {
        val registrationBean = FilterRegistrationBean<ApiKeyFilter>()
        registrationBean.filter = ApiKeyFilter(apiKey)
        registrationBean.addUrlPatterns("/api/*")
        return registrationBean
    }

    class ApiKeyFilter(private val validApiKey: String) : OncePerRequestFilter() {
        
        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
        ) {
            val requestApiKey = request.getHeader("X-API-Key")

            if (requestApiKey == null || requestApiKey != validApiKey) {
                response.status = HttpStatus.UNAUTHORIZED.value()
                response.contentType = "application/json"
                
                val errorResponse = ValidatePasswordErrorResponse(
                    code = 401,
                    message = "Missing or invalid X-API-Key header"
                )
                
                val objectMapper = ObjectMapper()
                response.writer.write(objectMapper.writeValueAsString(errorResponse))
                return
            }

            filterChain.doFilter(request, response)
        }
    }
}