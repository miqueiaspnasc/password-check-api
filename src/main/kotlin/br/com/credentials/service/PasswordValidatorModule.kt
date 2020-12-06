package br.com.credentials.service

import org.koin.dsl.module

val passwordValidatorModule = module {
    single { PasswordValidatorServiceImpl() as PasswordValidatorService }
}