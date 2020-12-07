package br.com.credentials.api

import br.com.credentials.App
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import io.javalin.plugin.json.JavalinJson
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PasswordCheckApiTest {
    private val app = App()
    private val url = "http://localhost:7000/password/check"

    @Before
    fun beforeEach() {
        app.start(7000)
        Thread.sleep(2_000)
    }

    @After
    fun afterEach() {
        app.stop()
    }

    @Test
    fun `Success when a password is valid`() {
        val response: HttpResponse<String> = Unirest.post(url)
            .body(JavalinJson.toJson(CredentialsData("2Da@3Jc0k9")))
            .asString()
        Assert.assertEquals(200, response.status)
    }

    @Test
    fun `Error when a password is invalid`() {
        val response: HttpResponse<String> = Unirest.post(url)
            .body(JavalinJson.toJson(CredentialsData("2Da~3Jc0kD")))
            .asString()
        Assert.assertEquals(400, response.status)
    }
}