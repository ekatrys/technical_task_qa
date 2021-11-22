package com.test.news.features.news

import    androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val NEWS_RESOURCE = "NEWS_RESOURCE"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(NEWS_RESOURCE)

    fun increment() = countingIdlingResource.increment()

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}