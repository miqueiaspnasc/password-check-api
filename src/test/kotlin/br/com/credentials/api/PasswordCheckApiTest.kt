package br.com.credentials.api

import br.com.credentials.App
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import io.javalin.plugin.json.JavalinJson
import org.junit.Assert
import org.junit.Test

class PasswordCheckApiTest {
    private val app = App()
    private val url = "http://localhost:7000/password/check"

    @Test
    fun `Success when a password is valid`() {
        app.start(7000)
        val response: HttpResponse<String> = Unirest.post(url)
            .body(JavalinJson.toJson(CredentialsData("2Da@3Jc0k9")))
            .asString()
        Assert.assertEquals(200, response.status)
        app.stop()
    }

    @Test
    fun `Error when a password is invalid`() {
        app.start(7000)
        val response: HttpResponse<String> = Unirest.post(url)
            .body(JavalinJson.toJson(CredentialsData("2Da~3Jc0kD")))
            .asString()
        Assert.assertEquals(400, response.status)
        app.stop()
    }
}