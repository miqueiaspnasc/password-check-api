package br.com.credentials.service

import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

import org.koin.test.inject

class PasswordValidatorServiceTest: KoinTest {

    private val passwordValidatorService by inject<PasswordValidatorService>()

    @Before
    fun beforeEach() {
        startKoin { modules(passwordValidatorModule) }
    }

    @After
    fun afterEach() {
        stopKoin()
    }

    @Test
    fun `invalid password - must have at least 9 characters`() {
        Assert.assertFalse(passwordValidatorService.isValid("1Da#2Bc0"))
    }

    @Test
    fun `invalid password - have at least one digit`() {
        Assert.assertFalse(passwordValidatorService.isValid("tDa#oBcxwy"))
    }

    @Test
    fun `invalid password - have at least one lowercase letter`() {
        Assert.assertFalse(passwordValidatorService.isValid("1DA#2BC034"))
    }

    @Test
    fun `invalid password - must have at least one uppercase letter`() {
        Assert.assertFalse(passwordValidatorService.isValid("1da#2bc034"))
    }

    @Test
    fun `invalid password - must have at least 1 special character`() {
        Assert.assertFalse(passwordValidatorService.isValid("1Daz2Bc034"))
    }

    @Test
    fun `invalid password - must have at least 1 allowed special character`() {
        Assert.assertFalse(passwordValidatorService.isValid("1Daz~Bc034"))
    }

    @Test
    fun `invalid password - must not have repeated characters`() {
        Assert.assertFalse(passwordValidatorService.isValid("1Da#2Bc03D"))
    }

    @Test
    fun `invalid password - must not have white space`() {
        Assert.assertFalse(passwordValidatorService.isValid("1Da#2Bc03 "))
    }

    @Test
    fun `valid password`() {
        Assert.assertTrue(passwordValidatorService.isValid("1Da#2Bc034"))
    }
}