package com.infbyte.amuze.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.infbyte.amuze.R
import com.infbyte.amuze.ui.dialogs.WalletAddressDialog
import com.infbyte.amuze.utils.openWebLink
import java.time.Instant
import java.time.Year
import java.util.Calendar

@Composable
fun AboutScreen(
    appName: String,
    appVersion: String,
    @DrawableRes appIconRes: Int,
    @StringRes privacyPolicyLinkRes: Int,
    socialLinks: List<String> = emptyList(),
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    var showWalletAddressDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var walletAddress by rememberSaveable {
        mutableStateOf("")
    }

    var currencyName by rememberSaveable {
        mutableStateOf("")
    }

    var currencyIcon by rememberSaveable {
        mutableIntStateOf(0)
    }

    fun showWalletAddressDialog(@StringRes addressId: Int, @StringRes nameId: Int, @DrawableRes currencyIconId: Int) {
        context.apply {
            walletAddress = getString(addressId)
            currencyName = getString(nameId)
        }
        currencyIcon = currencyIconId
        showWalletAddressDialog = true
    }

    if (showWalletAddressDialog) {
        WalletAddressDialog(walletAddress, currencyName, currencyIcon) {
            showWalletAddressDialog = false
        }
    }

    Box(
        Modifier.fillMaxSize().navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background),
        Alignment.Center
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painterResource(appIconRes),
                        contentDescription = "",
                        Modifier
                            .padding(top = 32.dp)
                            .size(160.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        appName,
                        Modifier.align(Alignment.BottomCenter),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            item {
                Text(
                    appVersion,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                Button(
                    onClick = {
                        context.openWebLink(privacyPolicyLinkRes)
                    },
                    Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(),
                    elevation = ButtonDefaults.filledTonalButtonElevation()
                ) {
                    Text(stringResource(R.string.amuze_privacy_policy))
                }
            }
            item {
                Column(
                    Modifier
                        .padding(top = 16.dp)
                        .fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        stringResource(R.string.amuze_sponsor),
                        Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        stringResource(R.string.amuze_appreciation),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        Modifier.fillMaxWidth().padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                showWalletAddressDialog(R.string.btc_address, R.string.amuze_btc, R.drawable.ic_btc)
                            },
                            Modifier.padding(end = 16.dp),
                            colors = ButtonDefaults.elevatedButtonColors(),
                            elevation = ButtonDefaults.elevatedButtonElevation()
                        ) {
                            Image(painterResource(R.drawable.ic_btc), contentDescription = "", Modifier.size(32.dp))
                            Text(stringResource(R.string.amuze_btc), Modifier.padding(start = 8.dp))
                        }
                        Button(
                            onClick = {
                                showWalletAddressDialog(R.string.eth_address, R.string.amuze_eth, R.drawable.ic_eth)
                            },
                            Modifier.padding(start = 16.dp),
                            colors = ButtonDefaults.elevatedButtonColors(),
                            elevation = ButtonDefaults.elevatedButtonElevation()
                        ) {
                            Image(painterResource(R.drawable.ic_eth), contentDescription = "", Modifier.size(32.dp))
                            Text(stringResource(R.string.amuze_eth), Modifier.padding(start = 8.dp))
                        }
                    }
                    Button(
                        onClick = { showWalletAddressDialog(R.string.usdc_address, R.string.amuze_usdc, R.drawable.ic_usdc) },
                        Modifier.padding(16.dp),
                        colors = ButtonDefaults.elevatedButtonColors(),
                        elevation = ButtonDefaults.elevatedButtonElevation()
                    ) {
                        Image(painterResource(R.drawable.ic_usdc), contentDescription = "", Modifier.size(32.dp))
                        Text(stringResource(R.string.amuze_usdc), Modifier.padding(start = 8.dp))
                    }
                }
            }
        }

        Column(
            Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    context.openWebLink(R.string.infbyte_website)
                },
                colors = ButtonDefaults.elevatedButtonColors(),
                elevation = ButtonDefaults.elevatedButtonElevation()
            ) {
                Image(painterResource(R.drawable.ic_language), contentDescription = "", Modifier.size(32.dp))
                Text(stringResource(R.string.amuze_website), Modifier.padding(start = 8.dp))
            }
            Text(stringResource(R.string.amuze_copyright, Char(169), Calendar.getInstance().get(Calendar.YEAR)), Modifier.padding(8.dp))
        }
    }
    BackHandler { onNavigateBack() }
}