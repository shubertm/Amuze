package com.infbyte.amuze.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.infbyte.amuze.R

@Composable
fun AppSettingsRedirectDialog(
    rationale: String,
    onAccept: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(Modifier.background(MaterialTheme.colorScheme.background, RoundedCornerShape(10))) {
            Text(rationale, Modifier.padding(16.dp))
            Row(
                Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FilledTonalButton(onClick = onDismiss) { Text(stringResource(R.string.amuze_cancel)) }
                Button(onClick = onAccept) { Text(stringResource(R.string.amuze_grant_in_settings)) }
            }
        }
    }
}
