package com.itau.password_validator.infrastructure.configs

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.itau.password_validator.infrastructure.configs.LogUtils.logStructured
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Configuration
class RequestResponseLoggingConfig {

    @Bean
    fun requestResponseLoggingFilter(): FilterRegistrationBean<RequestResponseLoggingFilter> {
        val registrationBean = FilterRegistrationBean<RequestResponseLoggingFilter>()
        registrationBean.filter = RequestResponseLoggingFilter()
        registrationBean.addUrlPatterns("/api/*")
        registrationBean.order = 1
        return registrationBean
    }

    class RequestResponseLoggingFilter : OncePerRequestFilter() {
        
        private val logger = LoggerFactory.getLogger(RequestResponseLoggingFilter::class.java)
        private val objectMapper = ObjectMapper()

        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
        ) {
            val wrappedRequest = ContentCachingRequestWrapper(request)
            val wrappedResponse = ContentCachingResponseWrapper(response)

            val traceId = java.util.UUID.randomUUID().toString()
            MDC.put("traceId", traceId)

            try {
                logRequest(wrappedRequest)
                filterChain.doFilter(wrappedRequest, wrappedResponse)
                logResponse(wrappedRequest, wrappedResponse)
            } finally {
                wrappedResponse.copyBodyToResponse()
                org.slf4j.MDC.clear()
            }
        }

        private fun logRequest(request: ContentCachingRequestWrapper) {
            val headers = request.headerNames.asSequence()
                .associateWith { headerName ->
                    if (headerName.equals("X-API-Key", ignoreCase = true)) "***MASKED***"
                    else request.getHeader(headerName)
                }

            logger.logStructured(
                "HTTP Request received",
                "HTTP_REQUEST",
                "method" to request.method,
                "uri" to request.requestURI,
                "headers" to headers
            )
        }

        private fun logResponse(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
            val responseBody = String(response.contentAsByteArray)

            logger.logStructured(
                "HTTP Response sent",
                "HTTP_RESPONSE",
                "method" to request.method,
                "uri" to request.requestURI,
                "status" to response.status,
                "body" to responseBody
            )
        }

        private fun maskSensitiveData(body: String): String {
            if (body.isBlank()) return body
            
            return try {
                val jsonNode = objectMapper.readTree(body)
                if (jsonNode.has("password")) {
                    val maskedJson = jsonNode.deepCopy<JsonNode>()
                    (maskedJson as ObjectNode)
                        .put("password", "***MASKED***")
                    objectMapper.writeValueAsString(maskedJson)
                } else {
                    body
                }
            } catch (e: Exception) {
                body
            }
        }
    }
}