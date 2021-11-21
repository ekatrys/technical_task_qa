package com.test.news.framework.extention

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

private fun actionOnView(viewMatcher: Matcher<View>, action: ViewAction) {
    onView(viewMatcher).perform(action)
}

private fun assertionOnView(viewMatcher: Matcher<View>, matcher: Matcher<View>) {
    onView(viewMatcher).check(matches(matcher))
}

fun Matcher<View>.click() = apply {
    actionOnView(this, ViewActions.click())
}

fun Matcher<View>.clearText() = apply {
    actionOnView(this, ViewActions.clearText())
}

fun Matcher<View>.isDisplayed() = apply {
    assertionOnView(this, ViewMatchers.isDisplayed())
}

fun Matcher<View>.hasErrorText(resourceId: Int) = apply {
    assertionOnView(
        this,
        ViewMatchers.hasErrorText(
            getResourceName(resourceId)
        )
    )
}

fun Matcher<View>.hasText(textResource: Int) = apply {
    assertionOnView(this, withText(textResource))
}

fun Matcher<View>.typeText(text: String) = apply {
    onView(this).perform(ViewActions.typeText(text), closeSoftKeyboard())
}