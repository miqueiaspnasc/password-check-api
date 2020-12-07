package br.com.credentials.api

import br.com.credentials.service.PasswordValidatorService
import io.javalin.http.Context
import org.koin.core.KoinComponent
import org.koin.core.inject

object PasswordValidatorController: KoinComponent {
    private val passwordValidatorService by inject<PasswordValidatorService>()

    fun validate(ctx: Context) {
        val credentialsData = ctx.body<CredentialsData>()
        ctx.status(if (passwordValidatorService.isValid(credentialsData.password)) 200 else 400)
    }
}