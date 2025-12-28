package com.itau.password_validator.application.services

import com.itau.password_validator.application.interfaces.IBusinessRuleOrchestratorService
import com.itau.password_validator.infrastructure.configs.LogUtils.logStructured
import com.itau.password_validator.domain.rules.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BusinessRuleOrchestratorService(
    minLengthRule: MinLengthRule,
    minUpperCaseRule: MinUpperCaseRule,
    minLowerCaseRule: MinLowerCaseRule,
    minDigitRule: MinDigitRule,
    specialCharsRule: SpecialCharsRule,
    whiteSpaceRule: WhiteSpaceRule,
    noRepeatedCharsRule: NoRepeatedCharsRule
) : IBusinessRuleOrchestratorService {

    private val logger = LoggerFactory.getLogger(BusinessRuleOrchestratorService::class.java)

    private val businessRules = listOf(
        minLengthRule,
        minUpperCaseRule,
        minLowerCaseRule,
        minDigitRule,
        specialCharsRule,
        whiteSpaceRule,
        noRepeatedCharsRule
    )

    override fun applyAllBusinessRule(password: String): List<String> {
        logger.logStructured("Starting business rules validation", "ORCHESTRATOR_START", "rulesCount" to businessRules.size)
        
        val violations = mutableListOf<String>()

        businessRules.forEach {
            val result = it.validate(password)
            if (!result.isValid) {
                violations.add(result.errorMessage?:"Unknown validation error")
            }
        }
        
        logger.logStructured("Business rules validation completed", "ORCHESTRATOR_FINISH", 
            "violationsFound" to violations.size, "rulesApplied" to businessRules.size)

        return violations
    }
}