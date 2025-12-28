package com.itau.password_validator.application.usecases

import com.itau.password_validator.application.interfaces.IBusinessRuleOrchestratorService
import com.itau.password_validator.infrastructure.configs.LogUtils.logStructured
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ValidatePasswordUseCase (
    private val businessRuleOrchestratorService : IBusinessRuleOrchestratorService
){
    private val logger = LoggerFactory.getLogger(ValidatePasswordUseCase::class.java)
    
    @Cacheable("passwordValidation")
    operator fun invoke(password : String) : PasswordValidateDTO {
        logger.logStructured("Starting password validation", "USECASE_START")
        
        val violations = businessRuleOrchestratorService.applyAllBusinessRule(password)
        val passwordIsValid = violations.isEmpty()
        
        logger.logStructured("Password validation finished", "USECASE_FINISH", 
            "isValid" to passwordIsValid, "violationsCount" to violations.size)

        return PasswordValidateDTO(isValid = passwordIsValid, violations = violations)
    }
}