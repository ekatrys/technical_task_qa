package com.test.news.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.test.news.features.news.domain.model.NewsModel
import com.test.news.features.news.presentation.NewsActivity
import com.test.news.framework.OffInternet
import com.test.news.pages.NewsPage
import org.junit.Rule
import org.junit.Test

class NewsActivityTest : BaseTest() {

    @get:Rule
    val intentsTestRule = IntentsTestRule(NewsActivity::class.java)

    @OffInternet
    @Test
    fun checkNoInternetError() {
        NewsPage().assertNoInternetErrorDisplayed()
    }

    @Test
    fun checkCorrectIntentForFirstNewsImageInSlider() {
        NewsPage {
            assertPageDisplayed()
            clickOnNewsByPosition(0)
            assertUriForOpenMatchesWithExpected(
                FIRST_NEWS_MODEL.imageUrlsList[0]
            )
        }
    }

    @Test
    fun checkCorrectIntentForNotFirstNewsImageInSlider() {
        val newsPosition = 0
        val imagePositionInSlider = 1

        NewsPage {
            assertPageDisplayed()
            clickOnImageFromSliderIntoNewsByPosition(
                imagePositionInSlider,
                newsPosition
            )
            assertUriForOpenMatchesWithExpected(
                FIRST_NEWS_MODEL.imageUrlsList[imagePositionInSlider]
            )
        }
    }

    companion object {
        private val FIRST_NEWS_MODEL = NewsModel.Slider(
            imageUrlsList = listOf(
                "https://www.newsbtc.com/wp-content/uploads/2020/01/altcoin-crypto-bitcoin-disbelief-wall-street-shutterstock_360101591-1200x780.jpg",
                "https://static01.nyt.com/images/2020/01/22/world/22israel-briefing/22israel-briefing-facebookJumbo.jpg",
                "https://cdn.cnn.com/cnnnext/dam/assets/200121163852-14-donald-trump-davos-world-economic-forum-0121-super-tease.jpg"
            )
        )
    }
}