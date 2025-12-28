package com.itau.password_validator.infrastructure.config

import org.slf4j.Logger
import org.slf4j.MDC
import java.time.LocalDateTime
import java.time.ZoneId

object LogUtils {
    
    private fun getBrasiliaTime(): String {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))?.toString() ?: LocalDateTime.now().toString()
    }
    
    fun Logger.logStructured(message: String, event: String, vararg extraFields: Pair<String, Any?>) {
        val traceId = MDC.get("traceId") ?: ""
        val timestamp = getBrasiliaTime()
        val fields = extraFields.joinToString(",") { "\"${it.first}\":${if (it.second is String) "\"${it.second}\"" else it.second}" }
        val extraJson = if (fields.isNotEmpty()) ",$fields" else ""
        
        this.info("{\"message\":\"$message\",\"event\":\"$event\",\"traceId\":\"$traceId\",\"timestamp\":\"$timestamp\"$extraJson}")
    }
    
    fun Logger.logError(message: String, exception: Exception) {
        val traceId = MDC.get("traceId") ?: ""
        val timestamp = getBrasiliaTime()
        val stackTrace = exception.stackTrace?.take(5)?.map { "${it.className}.${it.methodName}:${it.lineNumber}" }
        
        this.error("{\"message\":\"$message\",\"event\":\"ERROR\",\"traceId\":\"$traceId\",\"timestamp\":\"$timestamp\",\"exception\":\"${exception.javaClass.simpleName}\",\"stackTrace\":${stackTrace?.joinToString(",") { "\"$it\"" }.let { "[$it]" }}}")
    }
}