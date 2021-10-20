package com.ironical_groundwork.delivery

import android.app.Application
import com.onesignal.OneSignal

class DeliveryApp: Application() {

    private val ONESIGNAL_APP_ID = "d5758464-f96e-4f7c-a1cc-79f908a019fb"

    override fun onCreate() {
        super.onCreate()

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}