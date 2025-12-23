package com.itau.password_validator.application.service

import com.itau.password_validator.application.interfaces.IBusinessRuleOrchestratorService
import com.itau.password_validator.domain.rules.BlankSpaceRule
import com.itau.password_validator.domain.rules.DigitRule
import com.itau.password_validator.domain.rules.LowerCaseRule
import com.itau.password_validator.domain.rules.MinLengthRule
import com.itau.password_validator.domain.rules.NoRepeatedCharsRule
import com.itau.password_validator.domain.rules.SpecialCharsRule
import com.itau.password_validator.domain.rules.UpperCaseRule
import org.springframework.stereotype.Service

@Service
class BusinessRuleOrchestratorService : IBusinessRuleOrchestratorService {

    override fun applyAllBusinessRule(password: String): List<String> {
        val violations = mutableListOf<String>()

        val businessRules = listOf(
            BlankSpaceRule(),
            DigitRule(),
            LowerCaseRule(),
            MinLengthRule(),
            NoRepeatedCharsRule(),
            SpecialCharsRule(),
            UpperCaseRule(),
        )

        businessRules.forEach {
            val result = it.validate(password)
            if (!result.isValid){
                violations.add(result.errorMessage!!)
            }
        }

        return violations
    }
}