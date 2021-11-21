package com.test.news.framework.extention

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun Matcher<View>.clickByPosition(position: Int) = apply {
    onView(this)
        .perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    ViewActions.click()
                )
        )
}

fun Matcher<View>.scrollToPosition(position: Int) = apply {
    onView(this)
        .perform(
            RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(
                    position
                )
        )
}

fun childAtPosition(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("position $childPosition of parent ")
            parentMatcher.describeTo(description)
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item != null) {
                if (item.parent !is ViewGroup) return false
            }
            val parent = item?.parent as ViewGroup
            return (
                parentMatcher.matches(parent) &&
                    parent.childCount > childPosition &&
                    parent.getChildAt(childPosition) == item
                )
        }
    }
}