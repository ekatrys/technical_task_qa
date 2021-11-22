package com.test.news.framework.extention

import androidx.test.platform.app.InstrumentationRegistry

fun getResourceName(resourceId: Int): String {
    return InstrumentationRegistry
        .getInstrumentation()
        .targetContext
        .getString(resourceId)
}