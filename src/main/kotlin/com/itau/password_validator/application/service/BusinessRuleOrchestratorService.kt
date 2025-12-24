package com.itau.password_validator.application.service

import com.itau.password_validator.application.interfaces.IBusinessRuleOrchestratorService
import com.itau.password_validator.domain.rules.*
import org.springframework.stereotype.Service

@Service
class BusinessRuleOrchestratorService(
    minLengthRule: MinLengthRule,
    upperCaseRule: UpperCaseRule,
    lowerCaseRule: LowerCaseRule,
    digitRule: DigitRule,
    specialCharsRule: SpecialCharsRule,
    whiteSpaceRule: WhiteSpaceRule,
    noRepeatedCharsRule: NoRepeatedCharsRule
) : IBusinessRuleOrchestratorService {

    private val businessRules = listOf(
        minLengthRule,
        upperCaseRule,
        lowerCaseRule,
        digitRule,
        specialCharsRule,
        whiteSpaceRule,
        noRepeatedCharsRule
    )

    override fun applyAllBusinessRule(password: String): List<String> {
        val violations = mutableListOf<String>()

        businessRules.forEach {
            val result = it.validate(password)
            if (!result.isValid) {
                violations.add(result.errorMessage?:"Unknown validation error")
            }
        }

        return violations
    }
}