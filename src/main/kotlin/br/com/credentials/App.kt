package br.com.credentials

import br.com.credentials.api.PasswordValidatorController
import br.com.credentials.service.passwordValidatorModule
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class App {
    val logger: Logger = LoggerFactory.getLogger(App::class.java)
    val app = Javalin.create{ config ->
        config.requestLogger { ctx, ms ->
            logger.info("method=${ctx.method()}, path=${ctx.path()} responseTime=$ms, statusCode=${ctx.status()}")
        }
    }.apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("Resource not found") }
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
        stopKoin()
        app.stop()
    }
}