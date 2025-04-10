package com.infbyte.amuze.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

class GoogleMobileAdsConsentManager(private val context: Context) {

    private val consentInformation = UserMessagingPlatform.getConsentInformation(context)

    val canRequestAds = consentInformation.canRequestAds()

    internal var isPrivacyOptionsRequired by mutableStateOf(false)
        private set

    fun checkConsent(activity: Activity, onComplete: () -> Unit) {
        val params = ConsentRequestParameters.Builder()
            .build()

        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                    activity
                ) { error ->
                    if (error != null) {
                        Log.d("Consent loading failed:", error.message)
                        return@loadAndShowConsentFormIfRequired
                    }
                    onComplete()

                    updatePrivacyOptionsRequired()
                }
            },
            { error ->
                Log.d("Consent info update failed:", error.message)
            }
        )
    }

    fun showConsentForm(activity: Activity) {
        UserMessagingPlatform.showPrivacyOptionsForm(activity) { error ->
            if (error != null) {
                Log.d("Consent dismissed with error:", error.message)
                return@showPrivacyOptionsForm
            }
            updatePrivacyOptionsRequired()
        }
    }

    private fun updatePrivacyOptionsRequired() {
        isPrivacyOptionsRequired = consentInformation.privacyOptionsRequirementStatus ==
                ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
    }
}