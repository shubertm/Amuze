package com.infbyte.amuze_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infbyte.amuze.R
import com.infbyte.amuze.ui.dialogs.WalletAddressDialog
import com.infbyte.amuze_android.ui.theme.AmuzeAndroidTheme
import com.infbyte.amuzic.ui.screens.AboutScreen
import com.infbyte.amuzic.ui.screens.LoadingScreen
import com.infbyte.amuzic.ui.screens.NoMediaAvailableScreen
import com.infbyte.amuzic.ui.screens.NoMediaPermissionScreen
import com.infbyte.amuzic.ui.screens.NoSearchResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmuzeAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
        AboutScreen(
            appName = "Amuze",
            appVersion = "1.0",
            R.drawable.ic_amuzic_foreground,
            privacyPolicyLinkRes = R.string.amuze_privacy_policy
        ) {

        }
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
            appIcon = R.drawable.ic_amuzic_foreground,
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
