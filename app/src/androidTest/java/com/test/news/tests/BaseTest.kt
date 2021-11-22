package com.test.news.tests

import androidx.test.espresso.IdlingRegistry
import com.test.news.features.news.EspressoIdlingResource
import com.test.news.framework.OffInternetRule
import io.qameta.allure.android.rules.LogcatRule
import io.qameta.allure.android.rules.ScreenshotRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain

abstract class BaseTest {

    @get: Rule
    val ruleChain: RuleChain = RuleChain.emptyRuleChain()
        .around(OffInternetRule())
        .around(LogcatRule())
        .around(
            ScreenshotRule(
                mode = ScreenshotRule.Mode.FAILURE,
                screenshotName = "screenshot-failure"
            )
        )

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}