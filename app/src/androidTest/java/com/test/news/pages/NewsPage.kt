package com.test.news.pages

import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R
import com.test.news.framework.extention.*
import com.test.news.framework.reporting.step
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers

class NewsPage {
    constructor(action: NewsPage.() -> Unit) {
        this.action()
    }

    constructor()

    private val listNews = withId(R.id.recyclerViewNews)
    private val listSliderImage = withId(R.id.recyclerViewImageWidget)
    private val textViewError = withId(R.id.textViewError)

    fun clickOnNewsByPosition(position: Int) {
        step("click on first news") {
            listNews.clickByPosition(position)
        }
    }

    fun swipeNextImageIntoSliderNews(position: Int){
        step("click on first news") {
            listNews.swipeLeftImageIntoNewsSliderByPosition(position)
        }
    }

    fun assertPageDisplayed() = apply {
        step("Assert News page is displayed") {
            listNews.isDisplayed()
        }
    }

    fun assertNoInternetErrorDisplayed() = apply {
        step("Assert Error message correct and displayed") {
            textViewError.isDisplayed()
            textViewError.hasText(R.string.news_failed_to_load_message)
        }
    }

    fun assertUriForOpenMatchesWithExpected(url: String) {
        step("Uri in current intent matches with expected") {
            val expectedIntent = Matchers.allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(url)
            )
            Intents.intended(expectedIntent)
        }
    }

    fun openImageFromSliderIntoNewsByPosition(imagePlace: Int, newsPosition: Int) {
        childAtPosition(allOf(listNews,listSliderImage),1)
    }
}