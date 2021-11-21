package com.test.news.pages

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R
import com.test.news.framework.extention.*
import com.test.news.framework.reporting.step

class LoginPage {
    constructor(action: LoginPage.() -> Unit) {
        this.action()
    }
    constructor()

    private val userNameField = withId(R.id.editTextUserName)
    private val passwordField = withId(R.id.editTextPassword)
    private val loginBtn = withId(R.id.buttonLogin)

    fun assertPageDisplayed() = apply {
        step("Assert Login page is displayed") {
            userNameField.isDisplayed()
            passwordField.isDisplayed()
            loginBtn.isDisplayed()
        }
    }

    fun fillUserName(name: String) =
        step("Enter to UserName field $name") {
            userNameField.clearText()
            userNameField.typeText(name)
        }

    fun fillPassword(password: String) =
        step("Enter to Password field $password") {
            passwordField.clearText()
            passwordField.typeText(password)
        }

    fun pressLoginBtn() =
        step("Press login button") {
            loginBtn.click()
        }

    fun login(name: String, password: String) =
        step("Login with UserName = $name and password = $password") {
            fillUserName(name)
            fillPassword(password)
            pressLoginBtn()
        }

    fun openNewsAfterLoginUser(name: String, password: String): NewsPage {
        return step("Open news for user") {
            login(name, password)
            NewsPage {
                assertPageDisplayed()
            }
        }
    }

    fun assertWrongUserNameErrorAppear() =
        step("check wrong user name error is displayed") {
            userNameField.hasErrorText(R.string.login_wrong_user_name_error)
        }

    fun assertWrongPasswordErrorAppear() =
        step("check wrong user name error is displayed") {
            passwordField.hasErrorText(R.string.login_wrong_password_error)
        }
}