package com.itau.password_validator.infrastructure.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = CaffeineCacheManager("passwordValidation")
        cacheManager.setCaffeine(
            Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30)) // TTL de 30 minutos
                .maximumSize(1000)
        )
        return cacheManager
    }
}