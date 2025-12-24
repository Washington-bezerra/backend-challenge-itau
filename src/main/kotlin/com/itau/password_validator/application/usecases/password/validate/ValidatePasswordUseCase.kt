package com.itau.password_validator.application.usecases.password.validate

import com.itau.password_validator.application.interfaces.IBusinessRuleOrchestratorService
import org.springframework.stereotype.Service

@Service
class ValidatePasswordUseCase (
    val businessRuleOrchestratorService : IBusinessRuleOrchestratorService
){
    operator fun invoke(password : String) : PasswordValidateDTO {
        val violations = businessRuleOrchestratorService.applyAllBusinessRule(password)
        val passwordIsValid = violations.isEmpty()

        return PasswordValidateDTO(isValid = passwordIsValid, violations = violations)
    }
}