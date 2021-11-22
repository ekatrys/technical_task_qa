package com.test.news.framework

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.rules.ExternalResource
import org.junit.runner.Description
import org.junit.runners.model.Statement
import timber.log.Timber

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class OffInternet

class OffInternetRule : ExternalResource() {
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                if (readAnnotation(description)) {
                    offInternet(true)
                }
                base.evaluate()
                offInternet(false)
            }

            private fun readAnnotation(description: Description): Boolean =
                description.getAnnotation(OffInternet::class.java) != null


            private fun offInternet(isEnabled: Boolean) {
                val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                val internetState = if (isEnabled) "disable" else "enable"
                Timber.d(internetState)
                device?.executeShellCommand(MOBILE_DATA + internetState)
                device?.executeShellCommand(WIFI + internetState)

            }
        }

    companion object {
        private const val MOBILE_DATA = "svc data "
        private const val WIFI = "svc wifi "
    }
}