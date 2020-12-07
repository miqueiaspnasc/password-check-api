package br.com.credentials.service

class PasswordValidatorServiceImpl: PasswordValidatorService {
    override fun isValid(password: String): Boolean {
        return !password.contains("\\s".toRegex())
                && password.length >= 9
                && password.toSet().distinct().size == password.length
                && password.contains("\\d".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[a-z]".toRegex())
                && password.contains("[!@#$%^&*()\\-+]".toRegex())
    }
}