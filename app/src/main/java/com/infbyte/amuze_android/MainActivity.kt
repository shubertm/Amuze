package com.infbyte.amuze_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infbyte.amuze.R
import com.infbyte.amuze.ui.dialogs.WalletAddressDialog
import com.infbyte.amuze_android.ui.theme.AmuzeAndroidTheme
import com.infbyte.amuze.ui.screens.AboutScreen
import com.infbyte.amuze.ui.screens.LoadingScreen
import com.infbyte.amuze.ui.screens.NoMediaAvailableScreen
import com.infbyte.amuze.ui.screens.NoMediaPermissionScreen
import com.infbyte.amuze.ui.screens.NoSearchResultScreen
import com.infbyte.amuze.ui.views.AmuzeSeekBar
import com.infbyte.amuze.utils.GoogleMobileAdsConsentManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmuzeAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    AboutScreen(
                        "Amuze-Demo",
                        BuildConfig.VERSION_NAME,
                        R.drawable.ic_amuzic_foreground,
                        R.string.amuze_privacy_policy,
                        adsConsentManager = GoogleMobileAdsConsentManager(context)
                    ) { }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSeekBar() {
    AmuzeAndroidTheme {
        Box {
            AmuzeSeekBar()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewWalletAddressDialog() {
    AmuzeAndroidTheme {
        WalletAddressDialog(
            stringResource(R.string.btc_address),
            stringResource(R.string.amuze_btc),
            R.drawable.ic_btc
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAboutScreen() {
    AmuzeAndroidTheme {
        val context = LocalContext.current
        AboutScreen(
            appName = "Amuze",
            appVersion = "1.0",
            R.drawable.ic_amuzic_foreground,
            privacyPolicyLinkRes = R.string.amuze_privacy_policy,
            adsConsentManager = GoogleMobileAdsConsentManager(context)
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingScreen() {
    AmuzeAndroidTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoMediaPermissionScreen() {
    AmuzeAndroidTheme {
        NoMediaPermissionScreen(
            appIcon = R.drawable.amuzeo_intro,
            action = R.string.amuze_listen,
            onStartAction = {},
            onExit = {}
        ) {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNoMediaAvailableScreen() {
    AmuzeAndroidTheme {
        NoMediaAvailableScreen(
            R.string.amuze_no_videos,
            onRefresh = {},
            onExit = {}
        ) {

        }
    }
}

@Preview(showBackground = true, apiLevel = 30)
@Composable
fun PreviewNoSearchResultScreen() {
    AmuzeAndroidTheme {
        NoSearchResultScreen()
    }
}
