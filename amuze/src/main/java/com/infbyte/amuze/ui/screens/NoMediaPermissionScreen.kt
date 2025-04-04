package com.infbyte.amuze.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infbyte.amuze.R

@Composable
fun NoMediaPermissionScreen(
    @DrawableRes appIcon: Int,
    @StringRes action: Int,
    onStartAction: () -> Unit,
    onExit: () -> Unit,
    aboutApp: @Composable (() -> Unit) -> Unit
) {
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize().navigationBarsPadding().statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        var showAbout by rememberSaveable { mutableStateOf(false) }

        if (showAbout) {
            aboutApp { showAbout = false }
            return
        }

        IconButton(
            onClick = { showAbout = true },
            Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        ) {
            Icon(Icons.Outlined.Info, contentDescription = "")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val id = "appIcon"
            val inlineContent = mapOf(
                id to InlineTextContent(
                    Placeholder(32.sp, 32.sp, PlaceholderVerticalAlign.AboveBaseline)
                ) {
                    Icon(
                        painterResource(appIcon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
            val text = buildAnnotatedString {
                append("${stringResource(R.string.amuze_intro_1)} ")
                appendInlineContent(id, "[icon]")
                append(stringResource(R.string.amuze_intro_2))
            }

            Text(
                text,
                Modifier.padding(start = 0.dp, top = 31.dp),
                inlineContent = inlineContent,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Button(
                onClick = { onStartAction() },
                Modifier.padding(top = 64.dp),
                colors = ButtonDefaults.filledTonalButtonColors(),
                elevation = ButtonDefaults.filledTonalButtonElevation()
            ) {
                Text(stringResource(action))
            }
        }
    }

    BackHandler {
        onExit()
    }
}