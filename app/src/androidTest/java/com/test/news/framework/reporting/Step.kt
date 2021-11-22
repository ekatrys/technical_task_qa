package com.test.news.framework.reporting

import io.qameta.allure.kotlin.Allure.step
import timber.log.Timber

inline fun <T> step(description: String, crossinline action: () -> T): T {
    Timber.d("Espresso step", "------------ $description ------------ ")
    return step(description) {
        action()
    }
}