package com.itau.password_validator.application.interfaces

interface IBusinessRuleOrchestratorService {
    fun applyAllBusinessRule(password: String): List<String>
}