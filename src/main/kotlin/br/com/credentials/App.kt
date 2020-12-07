package br.com.credentials

import br.com.credentials.api.PasswordValidatorController
import br.com.credentials.service.passwordValidatorModule
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.core.context.startKoin

class App {

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }

    fun start(port: Int) {
        startKoin {
            printLogger()
            modules(passwordValidatorModule)
        }
        this.app.start(port)
        app.routes { post("/password/check", PasswordValidatorController::validate) }
    }

    fun stop() {
        app.stop()
    }
}