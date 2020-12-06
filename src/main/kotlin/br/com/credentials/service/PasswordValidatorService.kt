package br.com.credentials.service

interface PasswordValidatorService {
    fun isValid(password: String): Boolean
}