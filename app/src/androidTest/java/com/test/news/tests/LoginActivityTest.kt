package com.test.news.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.pages.LoginPage
import org.junit.Rule
import org.junit.Test

class LoginActivityTest : BaseTest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun checkLoginPageElementsVisible() {
        LoginPage().assertPageDisplayed()
    }

    @Test
    fun checkErrorWrongUsername() {
        LoginPage {
            fillUserName(INVALID_USER_NAME)
            pressLoginBtn()
            assertWrongUserNameErrorAppear()
        }
    }

    @Test
    fun checkErrorWrongPassword() {
        LoginPage {
            fillUserName(VALID_USER_NAME)
            fillPassword(INVALID_USER_PASSWORD)
            pressLoginBtn()
            assertWrongPasswordErrorAppear()
        }
    }

    @Test
    fun openNewsAfterSuccessLogin() {
        LoginPage {
            openNewsAfterLoginUser(
                name = VALID_USER_NAME,
                password = VALID_USER_PASSWORD
            )
        }
    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
        private const val INVALID_USER_NAME = "789"
        private const val INVALID_USER_PASSWORD = "123"
    }
}